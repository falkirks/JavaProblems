JavaProblems [![Build Status](https://magnum.travis-ci.com/Falkirks/JavaProblems.svg?token=4QK2uxFbcdYPyhixDggt&branch=master)](https://magnum.travis-ci.com/Falkirks/JavaProblems)
=========

This is my personal repository for storing school programming projects.

### Adding a new project 
- Create new project using IDEA.
- Make sure the project is compiling.
- Select build in the menu and then "Generate Ant build..."
- Pick ```singled-file```, and ```overwrite```, deselect ```use JDK definitions``` and set the ```output name``` to ```build```.
- Open .travis.yml in a text editor and add the project folder name to the ```PROJECT_DIR``` variable.
- Open the build.xml and remove ```-source 1.6``` (or other JDK version) from line ```70```.
- Push your changes and make sure the build passes.
