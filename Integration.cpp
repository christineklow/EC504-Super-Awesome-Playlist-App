#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include <sstream>

using namespace std;

void interpretCommand(string commandString)
{
  istringstream iss(commandString);
  char codeParam;
  if (codeParam == 's')
  {
    loadSongs();
    return;
  }
  iss >> codeParam;
  loadPlaylistData();
  commandString.erase(commandString.begin(), commandString.begin()+2)
  switch(codeParam)
  {
    case 'm' :
      addPlaylist(commandString)
      break;
    case 'a' :
      importPlaylists(commandString)
      break;
    case 't' :
      findSongPrefixes(commandString)
      break;
    case 'p' :
      getSongPlaylist(commandString)
      break;
    case 'l' :
      mostPopularPlaylist()
      break;
    default case :
      cout << "You done fucked up, son" << endl;
  }
}
