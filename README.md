JavaProblems [![Build Status](https://magnum.travis-ci.com/Falkirks/JavaProblems.svg?token=4QK2uxFbcdYPyhixDggt&branch=master)](https://magnum.travis-ci.com/Falkirks/JavaProblems)
=========

This is my personal repository for storing school programming projects.

### Adding a new project 
- Create new project using IDEA.
- Make sure the project is compiling.
- Select build in the menu and then "Generate Ant build..."
- Pick ```singled-file```, and ```overwrite```, deselect ```use JDK definitions``` and set the ```output name``` to ```build```.
- Open the build.xml and remove ```-source 1.6``` (or other JDK version) from line ```70```.
- Push your changes!

### Adding a project to Travis
- Make sure it is added according to above.
- Go to the ```Tracked Projects``` issue https://github.com/Falkirks/JavaProblems/issues/1
- Comment ```add {{project folder name}}```
- The bot will update the .travis.yml and the issue body.

