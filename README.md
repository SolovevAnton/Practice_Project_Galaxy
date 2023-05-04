# PracticeProject-galaxy

Practice project on working with XML parsing DOM and Jackson XML parsing
Done in 7.5 h
Refactored in 2h;

## Task about XML parsing
1. For each class, implement methods for loading data from an XML file and uploading it to it, the structure of which must correspond to the structure of the class. When implementing these methods, use Jackson Parser
2. For the Universe file, perform transformation in the static method (parameters: input file name and output file name) of the Util class according to the following feature:
   • All planets with the same name are grouped into tags
   <EqualGroup number=”i” name=””>…</EqualGroup>, where number is the tag number in the file, name is the name of the group of tags grouped by the given name
   • All EqualGroup tags must be placed in the Planets root tag
## Tasks for classes creations
1. Describe the class of a planet, (fields name of the planet, radius in km, period of revolution) and the planet behavior method , which returns a string containing the name of the planet and the speed of rotation around its axis. The rotation speed is calculated through the class method.
2. Describe the concept of a galaxy, (fields the name of the galaxy and a list of planets for the current galaxy) and the method of galaxy behavior , which refers to each planet from the galaxy, calls the behavior from item 1 for it. Returns a string containing all the results of each planet's behavior calls.
   2.1. Implement a method for adding a new planet to the list of all planets in the current galaxy. If such a planet already exists, then do not add it to the list
   2.2. Implement methods for finding a planet by its name in the galaxy (returns the planet object) and by the object of the planet itself (returns the occurrence index)
   2.3. Implement methods for removing a planet from a galaxy by its name (returns an object) and by the object of the planet itself (returns boolean)

3. Describe the concept of the universe, fields (at least a list of galaxies for the current universe) to determine independently
   3.1. In the universe, implement a method for adding a new galaxy to the list of all galaxies
   3.2. Implement methods:
   • searching for a planet from the universe by name (returns an object) and by object (returns an array of two indices: the index of the galaxy in the universe and the index of the planet in the found galaxy),
   • method of searching for a galaxy from the universe (by name and by object).
   3.3 Behavior method, defined as random generation every 30 seconds of a random number of galaxies with a random number of planets, randomly generate the names of galaxies and planets (implement a separate class for this with specialized methods): the name of the planet must begin with the letter P, followed by a sequence digits, the name of the galaxy is similar, starting with G.




