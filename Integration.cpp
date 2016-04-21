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
  iss >> codeParam;
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
      


  }



  for


}
