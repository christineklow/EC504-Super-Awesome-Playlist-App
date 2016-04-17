
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

/** Goals/Requirements:

	Able to fetch playlists by included song based on playlist popularity 	// DONE
		Playlist object with attribute song list, Popularity

	Able to display the most popular playlists 			// DONE
		Use data struct with most popular on top?

	Able to insert new playlist(s) (<=128 at a time, by file, or 1 at a time by UI) and least pop. playlist gets removed if playlistCount > 1024 	// DONE
		Pop the least popular off the bottom?

	Popularity of song == Sum(popularity of playlists song resides) 	// DONE
		Have Song object, Playlist object, each with lists of the other they are linked to?

	Must compute the complexity of each function

	Use C++ to minimize runtime?
**/

/** IMPLEMENTATION
	Data Structure Ideas: Binary Search Tree, map
	string for playlist name
	
	class for song, playlist?

	functions
	input manual 							// DONE
	input from text filename 

	error check is actual song 				// DONE

	display 8 most popular playlist 		// DONE
	remove least popular playlist 			// DONE

	given song, display most popular playlist
**/

struct songData {
        songData() : songID() {}
        songData(string newID)
         : songID(newID) {}

        string songID;
        int popularity;
        /*
        map<string,int> playlist;

        // list playlists song is in
        void listPlaylist(){
        	for( map<string,int>::iterator it = playlist.begin() ; it != playlist.end(); ++it)
        		cout<< it -> first << " "<< it -> second << endl;
        }

        void removePlaylist(string playlistName){
        	map<string,int>::iterator it = playlist.find(playlistName);
        	if(it != playlist.end()){
        		playlist.erase(it);		// if playlist exists in song's database, erase it
        		popularity--;			// decrement song's popularity
        	}
        }

        void addPlaylist(string playlistName, int playlistPopularity){

        	// if playlist already exists, update playlist popularity
        	map<string,int>::iterator it = playlist.find(playlistName);
        	if(it != playlist.end()){
        		it -> second += playlistPopularity;
        	}
        	else{	// if playlist doesn't exist, add to song's playlist database
        		playlist[playlistName] = playlistPopularity;
        		popularity++;	// update song popularity
        	}
        }*/
};

map<string,songData> songList;			// database of songs with the songID and popularity
map<int*,string> popularSongs;			// pointer to song list sorted by popularity
unordered_map<string,int> playlistDB;	// database of playlists with the popularity
multimap<int,string> popularityDB;		// database of playlists sorted by popularity

void loadSongs(){
	// load songs from song_list.txt into datastructure songList
	cout<<"Loading songs..." <<endl<<"..."<<endl;

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
/*
	for ( map<string,songData>::iterator it= songList.begin(); it!=songList.end(); ++it)
    cout << "SONG NAME: " << it->first << "     SONG ID: " << it->second.songID << endl;
*/
	cout<<"...\nSongs loaded."<<endl;

	file.close(); // close file

}

// returns iterator of song if it exists in database
map<string,songData>::iterator getSong(string searchName){ 	/*** finding a string using map is O(mlogn)  where m is string length, n is # of elements in map***/

	//cout<< "\nSearching for: " << searchName <<endl;

	map<string,songData>::iterator it;
	it = songList.find(searchName);		// Search for song in database 
	/*
	if (it != songList.end()){			// if song exists in database
    	cout << "SONG NAME: " << it->first << "     SONG ID: " << it->second.songID << endl;
    }*/
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
		cout<<"\nTop 8 Playlists: "<<endl;
		multimap<int, string>:: iterator it = songPlaylists.end();
		for( int count = 0; count < 8 && it != songPlaylists.begin() ; count++){ /*** O(n) where n is 8 ***/
			it--;
			cout<< it -> second << ": " << it -> first <<endl;
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

	// check to see if there are more than 1024 playlists, if so remove least popular
	removeLeastPopular();
}

bool importPlaylists(string filename){
	
	ifstream file;	// open file with playlists
	file.open(filename);

	if( file.fail() ) return 0;	// if file did not open sucessfully, return 0

	// finds playlists the song is in
	string playlistData;/*
	getline(file,playlistData,'\n');	// get playlist from line of file
	cout<<playlistData<<endl;
	addPlaylist(playlistData);
*/
	while( !file.eof() ){ 					
		getline(file,playlistData,'\n');					// get playlist from line of file
		if(playlistData != "") addPlaylist(playlistData);	// add the playlist to the database if its not an empty line
	}

	file.close();
	return 1;
}

// lists top 8 most popular playlists
void mostPopularPlaylist(){
	
	multimap<int, string>:: iterator it = popularityDB.end();	// the most popular playlists are at the end of the database
	// list top 8 playlists
	cout<<"\nTop 8 Playlists: "<<endl;
	for( int count = 0; count < 8 && it != popularityDB.begin() ; count++){
		it--;
		cout<< it -> second << ": " << it -> first <<endl;
	}
}


int main(){

	try{
		loadSongs();

		string userInput;
		importPlaylists("Datasets/all_playlists.txt");

		/*************************************/
		// generating test playlists
		/*playlistDB["1 2 3 4"] = 1;
		playlistDB["4 5 6"] = 2;
		playlistDB["2 5 6"] = 3;
		playlistDB["1 4 6"] = 4;
		playlistDB["7 4 8"] = 5;
		playlistDB["4 5 9"] = 6;
		playlistDB["4 5 10"] = 7;
		playlistDB["4 8 6"] = 8;
		playlistDB["3 4 6"] = 9;
		playlistDB["7 5 4"] = 10;
		playlistDB["1 5 4"] = 11;
		playlistDB["3 4 9"] = 12;
		playlistDB["4 9 6"] = 13;
		playlistDB["4 5 6"] = 14;

		// add playlist to popularityDB
		for(unordered_map<string, int>:: iterator it = playlistDB.begin(); it != playlistDB.end(); ++it){
			popularityDB.insert( std::pair<int,string>(it->second, it->first) );
		}
	*/
		/************************************

		// testing importing playlist
		cout << "\nEnter playlist file: ";
		getline(std::cin,userInput);

		importPlaylists(userInput);

		/*************************************/

		// testing song searching
		cout << "\nEnter Song to Search: ";
		getline(std::cin,userInput);
		getSongPlaylist(userInput);

		// testing song popularity
		cout<< userInput << " Popularity: " <<updateSongPopularity(userInput) <<endl;


		/*************************************/

		// testing adding playlist and listing most popular
		cout << "\nEnter Playlist to add: ";
		getline(std::cin,userInput);
		addPlaylist(userInput);

		mostPopularPlaylist();

		/*************************************/

	}
	catch(const char* msg){ // Error in opening file
		cerr << msg << endl;
	}

	return 0;
}