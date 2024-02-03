# 👋 Welcome to UpBlock! 👋

## This plugin was created for the sole purpose to be able to show what I, Lutto, really can do.

Welcome to my plugin! This project was built with **gradle** and currently has some nice features such as:
* Discord bot integration
* Custom Rank System
* Database Integration
* Custom Tab List
* Custom Player Join messages
* Coming soon!

**_WARNING_**: This is not an api, this is a project made by me to be able to practice my plugin making skills in the real world!

## Watch the showcase video!

Coming soon!

## How to install this project on your computer

Before actually setting the server with the plugin up, you need to fulfil some **prerequisites**: An SQL Server 
(this can be local or server, you choose), Gradle installed & A Discord Bot. 

1. Clone the Github Repository:
`git clone https://github.com/StillLutto/UpBlock`
2. Then create a local spigot server.
3. Open build.gradle.kts and change line 54 to your local server's plugins folder.
4. Run ./gradlew in the terminal & run the shadowJar Task in the right Gradle pane.
5. Then make a .env file in the root directory of the server and put the following values in here:

```
token=
host=
port=
database=
username=
password=
```

For the token, you have to put your discord token in there, make sure to not share that with anyone else! The rest you can easily find in your Database Viewer. 

If you ever have any question or problems, just **message me on discord**! (.lutto)

[//]: # (TODO: DEVLOPER INSTALL)
[//]: # (## Developer install)

## Find a bug?

Please make sure that before you create a pull request, make a github issue and link that to the pull request like this: `fix: #13`

## Want to collaborate?

If you want to collaborate with my, please hit me up on my discord: `.lutto `