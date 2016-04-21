
/** 
	Playlist App
	Christine Low
	Shane McCormack
	Alexandra Nero
**/
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include <map>
#include <utility>
#include <unordered_map>

using namespace std;

struct songData {
        songData() : songID() {}
        songData(string newID)
         : songID(newID) {}
         songData(string newID, int pop)
         : songID(newID), popularity(pop) {}

        string songID;
        int popularity;
};

map<string,songData> songList;			// database of songs with the songID and popularity
unordered_map<string,int> playlistDB;	// database of playlists with the popularity
multimap<int,string> popularityDB;		// database of playlists sorted by popularity
bool playlistChange = true;				// keeps track if any changes to the playlist database has been made

void loadSongs(){
	// load songs from song_list.txt into datastructure songList

	ifstream file;	// open file with song list
	file.open("Datasets/song_list.txt");

	if( file.fail() ) throw "Error in loading songs";	// if file did not open sucessfully, throw exception

	// finds playlists the song is in
	string tempSong, tempArtist, tempID;

	while( !file.eof() ){ 					/*** O(n) where n is # of characters in file ***/
		getline (file,tempID,'\t');
		getline (file,tempSong,'\t');
		getline (file,tempArtist,'\n');
		songList[tempSong] = songData(tempID);
	}

	file.close(); // close file

}

// returns iterator of song if it exists in database
map<string,songData>::iterator getSong(string searchName){ 	/*** finding a string using map is O(mlogn)  where m is string length, n is # of elements in map***/

	map<string,songData>::iterator it;
	it = songList.find(searchName);		// Search for song in database 

	return it;

}

// returns song popularity
int updateSongPopularity(string songName){

	// first obtain pointer to song if it exists
	map<string,songData>::iterator songSearch = getSong(songName);

	int songPopularity = 0;
	string tempSong;

	if(songSearch != songList.end()){ 	// song exists in database

		// search playlist database for playlists that contains song
		for( unordered_map<string, int>:: iterator it = playlistDB.begin(); it != playlistDB.end(); ++it){ /*** O(nm) where n is # of elements in playlistDB, m is length of playlist name ***/

			stringstream ss(it -> first);
			while(getline(ss,tempSong,' ')){					// get playlist songs from input
				if(tempSong == songSearch -> second.songID){	// if the playlist contains the song
					songPopularity += it -> second; 			// update song popularity based on sum of playlist popularity
					break;
				}
			}
		}

		songSearch -> second.popularity = songPopularity;

	}

	return songPopularity;
}

void findSongPrefixes(string prefix) {
    map<string,songData>::const_iterator it = songList.lower_bound(prefix); // looks for song that starts with prefix
    int prefixLength = prefix.size();
    string songMatch;
    multimap<int,string> songsWithPrefix;	// list of songs that has the prefix sorted by popularity 

    if( it != songList.end() ){ 			//if there is a song that starts with the prefix
	    do{
	        songMatch = it->first;
	        if (songMatch.compare(0, prefixLength, prefix) == 0){ 			// check to see it if it a prefix
	            if(playlistChange) updateSongPopularity(it -> first);		// update song's popularity if there was a change to the playlist databse
	            songsWithPrefix.insert( std::pair<int,string>(it -> second.popularity, it -> first) );	// add song to map sorted by popularity
	        }
	        it++;
	    }while(songMatch.compare(0, prefixLength, prefix) == 0); // check if there is anymore songs with prefix
	}

	playlistChange = false;				// update to show changes in playlist database has been taken into account

	multimap<int, string>:: iterator iter = songsWithPrefix.end();
	for( int count = 0; count < 4 && iter != songsWithPrefix.begin() ; count++){ /*** O(n) where n is 8 ***/
		iter--;
		cout<< iter -> second<<endl;	// print out songs sorted by popularity
	}
}

// returns playlists that contain songs
void getSongPlaylist(string searchName){

	// first obtain song id number if it exists
	map<string,songData>::iterator songSearch = getSong(searchName);

	if(songSearch != songList.end()){ 	// song exists in database

		multimap<int,string> songPlaylists;	// list of playlists that contains the specific song
		int songPopularity = 0;
		string tempSong;

		// search playlist database for playlists that contains song
		for( unordered_map<string, int>:: iterator it = playlistDB.begin(); it != playlistDB.end(); ++it){ /*** O(nm) where n is # of elements in playlistDB, m is length of playlist name ***/

			stringstream ss(it -> first);
			while(getline(ss,tempSong,' ')){					// get playlist songs from input
				if(tempSong == songSearch -> second.songID){
					songPlaylists.insert( std::pair<int,string>(it -> second, it -> first) );	// add playlist to songPlaylists sorted automatically by popularity
					songPopularity += it -> second; 			// update song popularity based on sum of playlist popularity
					break;
				}
			}

		}

		songSearch -> second.popularity = songPopularity;	// update song popularity

		// list top 8 playlists
		multimap<int, string>:: iterator it = songPlaylists.end();
		for( int count = 0; count < 8 && it != songPlaylists.begin() ; count++){ /*** O(n) where n is 8 ***/
			it--;
			cout<< it -> second <<endl;
		}


	}
	else cout<<"Song does not exist in database"<<endl;
}

// function removes least popular playlist from the database if there are more than 1024 playlists
void removeLeastPopular(){
	if( playlistDB.size() >= 1024 ){
		multimap<int, string>:: iterator popIter = popularityDB.begin();	// first playlist of the popularityDB is the least popular
		unordered_map<string, int>:: iterator playlistIter;

		//look for least popular playlist and remove it
		playlistIter = playlistDB.find(popIter -> second);		// get pointer to least popular playlist in playlist databse
		playlistDB.erase(playlistIter);							// erase the playlist from playlistDB
		popularityDB.erase(popIter);							// erase the playlist from popularity database
	}
}

void updatePlaylistPopularity(int oldPopularity, string playlistName, int newPopularity){
	typedef multimap<int, string>:: iterator iterator;
	std::pair<iterator, iterator> iterpair = popularityDB.equal_range(oldPopularity);

	// erase playlist with old popularity
	iterator it = iterpair.first;
	for (; it != iterpair.second; ++it) {
	    if( it -> second == playlistName) { 
	        popularityDB.erase(it);
	        break;
	    }
	}

	// insert playlist with updated popularity
	popularityDB.insert( std::pair<int,string>(newPopularity, playlistName) );
}

// adds playlist to database manually
void addPlaylist(string playlistInput){

	string playlistSongs, temp_popularity;
	int popularity;

	// playlist input must be in the format <Playlist \t Popularity(integer)>
	stringstream ss(playlistInput);
	getline(ss,playlistSongs,'\t');					// get playlist songs from input
	getline(ss, temp_popularity, '\n');				// get popularity
	popularity = stoi(temp_popularity,nullptr,10); 	// convert popularity from string to in

	// check to see if playlist has been added already
	unordered_map<string, int>:: iterator it = playlistDB.find(playlistSongs);
	if( it != playlistDB.end() ){						// if playlist is already in database
		int newPopularity = it -> second + popularity;	// new popularity of playlist

		updatePlaylistPopularity(it -> second, playlistSongs, newPopularity);	// update popularity in popularityDB
		it -> second = newPopularity;	// update popularity of playlist in playlistDB
	}
	else{	// if it does not exist yet, add it to the database
		playlistDB[playlistSongs] = popularity; 
		popularityDB.insert( std::pair<int,string>(popularity, playlistSongs) );
	}

	playlistChange = true;

	// check to see if there are more than 1024 playlists, if so remove least popular
	removeLeastPopular();
}

int importPlaylists(string filename){
	
	ifstream file;	// open file with playlists
	file.open(filename);

	if( file.fail() ) return 0;	// if file did not open sucessfully, return 0

	// finds playlists the song is in
	string playlistData;

	int count = 0;

	while( !file.eof() ){ 					
		getline(file,playlistData,'\n');					// get playlist from line of file
		if(playlistData != "") addPlaylist(playlistData);	// add the playlist to the database if its not an empty line

		count++;
		if(count == 128) return 2; // return 2 if there are more than 128 lines
	}

	playlistChange = true;

	file.close();
	return 1;
}

// lists top 8 most popular playlists
void mostPopularPlaylist(){
	
	multimap<int, string>:: iterator it = popularityDB.end();	// the most popular playlists are at the end of the database
	// list top 8 playlists
	for( int count = 0; count < 8 && it != popularityDB.begin() ; count++){
		it--;
		cout<< it -> second <<endl;
	}
}

// loads playlist database that was from the same session
void loadPlaylistData(){
	
	ifstream file;	// open file with playlists
	file.open("memory/playlistData.txt");

	if( file.fail() ) throw "Error in loading playlists"; // if file did not open sucessfully, throw exception

	string playlistData; // finds playlists the song is in

	while( !file.eof() ){ 					
		getline(file,playlistData,'\n');					// get playlist from line of file
		if(playlistData != "") addPlaylist(playlistData);	// add the playlist to the database if its not an empty line
	}

	file.close();
}

// saves playlist database from the same session
void savePlaylistData(){
	
	ofstream file;	// output playlist data
	file.open("memory/playlistData.txt");

	if( file.fail() ) throw "Error in saving playlists"; // if file did not open sucessfully, throw exception

	// output playlist
	for( unordered_map<string, int>:: iterator it = playlistDB.begin(); it != playlistDB.end(); ++it){

		file << it -> first << "\t" << it -> second <<"\n"; // prints it out with playlist name then popularity
	}

	file.close();
}

// loads song data from the same session
void loadSongData(){
	
	ifstream file;	// open file with playlists
	file.open("memory/songData.txt");

	if( file.fail() ) throw "Error in loading songs"; // if file did not open sucessfully, throw exception

	string playlistData; // finds playlists the song is in

	// finds playlists the song is in
	string tempSong, tempID, tempPopularity;
	int popularity;

	while( !file.eof() ){ 
		getline (file,tempID,'\t');
		getline (file,tempSong,'\t');
		getline (file,tempPopularity,'\n');
		popularity = stoi(tempPopularity,nullptr,10); 	// convert popularity from string to in
		songList[tempSong] = songData(tempID,popularity);
	}

	file.close();
}

// saves song data from the same session
void saveSongData(){
	
	ofstream file;	// open file with playlists
	file.open("memory/songData.txt");

	if( file.fail() ) throw "Error in saving songs"; // if file did not open sucessfully, throw exception

	string playlistData; // finds playlists the song is in

	// finds playlists the song is in
	string tempSong, tempArtist, tempID, tempPopularity;
	int popularity;

	for(map<string,songData>::iterator it = songList.begin(); it != songList.end(); ++it){ 
		file << it -> second.songID << "\t" << it -> first << "\t" << it -> second.popularity <<"\n"; // prints out songID, name, then popularity
	}

	file.close();
}

// loads status of if playlist database has been changed from the session
void loadPlaylistStatus(){

	ifstream file;	// open file with playlists
	file.open("memory/playliststatus.txt");

	if( file.fail() ) throw "Error in loading playlist database status"; // if file did not open sucessfully, throw exception

	string tempStatus;

	getline (file,tempStatus,'\n');

	if(tempStatus == "1") playlistChange = true;
	else playlistChange = false;

	file.close();
}

// saves status of if playlist database has been changed from the session
void savePlaylistStatus(){

	ofstream file;	// open file with playlists
	file.open("memory/playliststatus.txt");

	if( file.fail() ) throw "Error in saving playlist database status"; // if file did not open sucessfully, throw exception

	file << playlistChange << "\n";

	file.close();
}


int main(){

	try{
		loadSongs();
		//importPlaylists("Datasets/all_playlists.txt");

		string userInput;

		//loadPlaylistData();
		//loadSongData();
		//loadPlaylistStatus();

		/*************************************

		// testing song searching
		cout << "\nEnter Song to Search: ";
		getline(std::cin,userInput);
		getSongPlaylist(userInput);

		// testing song popularity
		cout<< userInput << " Popularity: " << updateSongPopularity(userInput) <<endl;

		// test finding prefixes
		cout << "\nEnter prefix: ";
		getline(std::cin,userInput);
		findSongPrefixes(userInput);

		// testing adding playlist and listing most popular
		cout << "\nEnter Playlist to add: ";
		getline(std::cin,userInput);
		addPlaylist(userInput);
	
		// test listing most popular playlist
		mostPopularPlaylist();

		*************************************/

		//saveSongData();
		//savePlaylistData();
		//savePlaylistStatus();


	}
	catch(const char* msg){ // Error in opening file
		cerr << msg << endl;
	}

	return 0;
}