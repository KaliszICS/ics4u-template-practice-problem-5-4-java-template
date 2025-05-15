# Instructions  

1. Create an interface called Player</br>

it must have the following methods:</br>
void start()</br>
void stop()</br>
void volumeUp()</br>
void volumeDown()</br>
int getVolume()</br>

2. Create the 3 following classes:</br>
MusicPlayer -</br>

Has the following variables:</br>
onOff - boolean</br>
musicList - String[]</br>
currentSong - String</br>
volume - int</br>

Constructor that takes in a String[]</br>
Set the String[] to musicList</br>
Set volume to 0</br>
Set onOff to false</br>
Set currentSong to an empty string</br>

the following methods:</br>
void start() - if onOff is false, make it true, set currentSong to the first index of musicList</br>
void stop() - if onOff is true, make it false, set currentSong to an empty String</br>
boolean getOnOff() - returns onOff</br>
void volumeUp() - increase volume by 1</br>
void volumeDown() - decrease volume by 1</br>
int getVolume() - return the volume</br>
void next() - set currentSong to next song in musicList</br>
void previous() - set currentSong to previous song in musicList</br>
String getCurrentSong() - return currentSong</br>

RadioPlayer</br>
onOff - boolean</br>
stationList - double[]</br>
volume - int</br>
station - double</br>

Constructor that takes in a double[]</br>
Set the double[] to stationList</br>
Set volume to 0</br>
Set onOff to false</br>
Set station to 0</br>

the following methods:</br>
void start() - if onOff is false, make it true, set station to the first index of stationList</br>
void stop() - if onOff is true, make it false, set station to 0</br>
boolean getOnOff() - returns onOff</br>
void volumeUp() - increase volume by 2</br>
void volumeDown() - decrease volume by 2</br>
int getVolume() - return the volume</br>
void next() - set station to next station in stationList</br>
void previous() - set station to previous station in stationList</br>
double getStation() - return station</br>

VideoPlayer</br>
onOff - boolean</br>
video - String</br>
currentTime - int</br>
volume - int</br>

Constructor that takes in a String</br>
set the String to video</br>
Set volume to 0</br>
Set onOff to false</br>
Set currentTime to 0</br>

the following methods:</br>
void start() - if onOff is false, make it true</br>
void stop() - if onOff is true, make it false</br>
boolean getOnOff() - returns onOff</br>
void volumeUp() - increase volume by 5</br>
void volumeDown() - decrease volume by 5</br>
int getVolume() - return the volume</br>
void fastForward() - move the current time forward by 5</br>
void rewind() - move the current time back by 5</br>
int getCurrentTime() - return the current time</br>
String getVideo() - return video</br>
void setVideo(String video) - set the video</br>
