
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

using namespace std;

/** Goals/Requirements:

	Able to fetch playlists by included song based on playlist popularity
		Playlist object with attribute songList, Popularity

	Able to display the most popular playlists
		Use data struct with most popular on top?

	Able to insert new playlist(s) (<=128 at a time, by file, or 1 at a time by UI) and least pop. playlist gets removed if playlistCount > 1024
		Pop the least popular off the bottom?

	Popularity of song == Sum(popularity of playlists song resides)
		Have Song object, Playlist object, each with lists of the other they are linked to?

	Must compute the complexity of each function

	Use C++ to minimize runtime?
**/

/** IMPLEMENTATION
	Data Structure Ideas: Binary Search Tree, hash for playlist
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

map<string,string> songList;

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
		songList[tempSong] = tempID;
	}

	for ( map<string,string>::iterator it= songList.begin(); it!=songList.end(); ++it)
    cout << "SONG NAME: " << it->first << "     SONG ID: " << it->second << endl;

	cout<<"...\nSongs loaded."<<endl;

	file.close(); // close file

}

// returns ID of song if it exists in database
string getSongID(string searchName){

	cout<< "\nSearching for: " << searchName <<endl;

	map<string,string>::iterator it;
	it = songList.find(searchName);		// Search for song in database
	
	if (it != songList.end()){			// if song exists in database
    	cout << "SONG NAME: " << it->first << "     SONG ID: " << it->second << endl;
    	return it -> second;
    }
	else return "-1"; // Song does not exist

}

// returns playlists that contain songs
void getSongPlaylist(string searchName){

	// first obtain song id number if it exists
	string songID = getSongID(searchName);

	if(songID != "-1"){
		// lists top 8 playlists song is in
		cout<<"\nTop 8 Playlists: "<<endl;
	}
	else cout<<"Song does not exist in database"<<endl;
}

int main(){

	try{
		loadSongs();
	}
	catch(const char* msg){ // Error in opening file
		cerr << msg << endl;
	}
	
	string searchName;
	cout << "\nEnter Song to Search: ";
	getline(std::cin,searchName);
	getSongPlaylist(searchName);

	return 0;
}