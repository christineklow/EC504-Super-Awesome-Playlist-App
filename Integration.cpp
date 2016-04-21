#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include <sstream>
#include <stdio.h>


using namespace std;

int main( int argc, char *argv[] )
{
  string argString = str(argv);
  argString.erase(argString.begin(), argString.begin()+8)   #stripping out arg[0]
  interpretCommand(argString);
  return;
}

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

/*

int main( int argc, char *argv[] )  {

   if( argc == 2 ) {
      printf("The argument supplied is %s\n", argv[1]);
   }
   else if( argc > 2 ) {
      printf("Too many arguments supplied.\n");
   }
   else {
      printf("One argument expected.\n");
   }
}
*/
