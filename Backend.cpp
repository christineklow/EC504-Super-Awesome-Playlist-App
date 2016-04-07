
/** 
	Playlist App
	Christine Low
	Shane McCormack
	Alexandra Nero
**/
#include <iostream>
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
}

int main(){
	return 0;
}