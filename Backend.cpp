
/** 
	Playlist App
	Christine Low
	Shane McCormack
	Alexandra Nero
**/
#include <iostream>
<<<<<<< HEAD
=======
#include <string>
#include <fstream>
#include <sstream>
#include <map>
#include <utility>
#include <unordered_map>

>>>>>>> origin/master
using namespace std;

/** Goals/Requirements:

	Able to fetch playlists by included song based on playlist popularity
		Playlist object with attribute song list, Popularity

	Able to display the most popular playlists
		Use data struct with most popular on top?

	Able to insert new playlist(s) (<=128 at a time, by file, or 1 at a time by UI) and least pop. playlist gets removed if playlistCount > 1024
		Pop the least popular off the bottom?

	Popularity of song == Sum(popularity of playlists song resides)
		Have Song object, Playlist object, each with lists of the other they are linked to?

	Must compute the complexity of each function

	Use C++ to minimize runtime?
**/

<<<<<<< HEAD
/** IMPLEMENTATION
	Data Structure Ideas: Binary Search Tree
	string for playlist name
	
	class for song, playlist

	functions
	input manual
	input from text filename 

	error check is actual song

	display 8 most popular playlist
	remove least popular playlist

	given song, display most popular playlist
**/

// returns data structure listing playlists that contain songs
void getSong(){
	//
=======
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
unordered_map<string,int> playlistDB;	// database of playlists with the popularity
map<int,string> popularityDB;			// databse of playlists sorted by popularity

void loadSongs(){
	// load songs from song_list.txt into datastructure songList
	cout<<"Loading songs..." <<endl<<"..."<<endl;

	ifstream file;	// open file with song list
	file.open("song_list.txt");

	if( file.fail() ) throw "Error in loading songs";	// if file did not open sucessfully, throw exception

	// finds playlists the song is in
	string tempSong, tempArtist, tempID;

	while( !file.eof() ){
		getline (file,tempID,'\t');
		getline (file,tempSong,'\t');
		getline (file,tempArtist,'\n');
		songList[tempSong] = songData(tempID);
	}

	for ( map<string,songData>::iterator it= songList.begin(); it!=songList.end(); ++it)
    cout << "SONG NAME: " << it->first << "     SONG ID: " << it->second.songID << endl;

	cout<<"...\nSongs loaded."<<endl;

	file.close(); // close file

}

// returns iterator of song if it exists in database
map<string,songData>::iterator getSong(string searchName){

	cout<< "\nSearching for: " << searchName <<endl;

	map<string,songData>::iterator it;
	it = songList.find(searchName);		// Search for song in database
	
	if (it != songList.end()){			// if song exists in database
    	cout << "SONG NAME: " << it->first << "     SONG ID: " << it->second.songID << endl;
    }
	return it;

}

// returns playlists that contain songs
void getSongPlaylist(string searchName){

	// first obtain song id number if it exists
	map<string,songData>::iterator songSearch = getSong(searchName);

	if(songSearch != songList.end()){ 	// song exists in database

		map<int,string> songPlaylists;	// list of playlists that contains the specific song
		int songPopularity = 0;

		// search playlist database for playlists that contains song
		for( unordered_map<string, int>:: iterator it = playlistDB.begin(); it != playlistDB.end(); ++it){

			// if the playlist contains the song
			if(it-> first.find( songSearch -> second.songID) != string::npos){
				songPlaylists[it -> second] = it -> first;	// add playlist to songPlaylists sorted automatically by popularity
				songPopularity++; 							// increment song popularity based on how many playlists its in
			}
		}

		songSearch -> second.popularity = songPopularity;	// update song popularity

		// list top 8 playlists
		cout<<"\nTop 8 Playlists: "<<endl;
		map<int, string>:: iterator it = songPlaylists.end();
		for( int count = 0; count < 8 && it != songPlaylists.begin() ; count++){
			it--;
			cout<< it -> second << ": " << it -> first <<endl;
		}
	}
	else cout<<"Song does not exist in database"<<endl;
>>>>>>> origin/master
}

void removeLeastPopular(){
	if( playlistDB.size() > 1024 ){
		//look for least popular and remove least popular

	}
}

// adds playlist to database
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
	if( it != playlistDB.end() ){					// playlist is already in database
		it -> second = it -> second + popularity;	// update popularity of playlist
	}
	else playlistDB[playlistSongs] = popularity; 	// if it does not exist yet, add it

	// check to see if there are more than 1024 playlists, if so remove least popular
	removeLeastPopular();
}


int main(){
<<<<<<< HEAD
=======

	try{
		loadSongs();
	}
	catch(const char* msg){ // Error in opening file
		cerr << msg << endl;
	}


	// generating test playlists
	playlistDB["1 2 3 4"] = 1;
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

	string searchName;
	cout << "\nEnter Song to Search: ";
	getline(std::cin,searchName);
	getSongPlaylist(searchName);

	//addPlaylist(searchName);

>>>>>>> origin/master
	return 0;
}