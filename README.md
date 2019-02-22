Smith College / CSC212 / Aquarium Starter Code

## How to import this project into Eclipse

There are a lot of steps here! Don't try to memorize them. Whenever you click next, or open a menu, see if you can read it, guess what you should do next, and confirm with these steps. Doing it this way will help you get through it quicker for future assignments.

1. Go to the "File" menu and choose "Import"
2. Expand the Git Folder and choose "Projects from Git"
3. Click "Next"
4. Choose "Clone URI"
5. Copy and paste [https://github.com/jjfiv/CSC212P1.git](https://github.com/jjfiv/CSC212P1.git) into the URI field.
6. Click "Next"
7. Wait for it to find "master"
8. Click "Next"
9. (optional) Choose a directory to store the project.
10. Click "Next"
11. Choose "Import as general project"
12. Click "Next"
13. Click "Finish"
14. Right-Click on the new project that showed up in the "Package Explorer" on the left. Select "Configure > Convert to Maven Project".

# Assignment P1: Aquarium Grading Rubric

***Due Date***: February 7, 2019.

Remember that we will have P2 opening on that day, so don't save it until the last minute!

- This assignment is composed of a total of ***64 mandatory points*** and ***79 optional points***. That's too many points in total.
- The assignment ***will be graded out of 100***. 
- This means that the best you can acquire is much higher than 100, but it will ***not be used***. Someone who submits only 100 points and completes their functionality perfectly will have an equivalent grade to someone who does everything, so use your time wisely.
- Therefore, it probably makes sense to do a little extra, but not every single task.

## Mandatory Goals (sums to 64):

### Rubric and Reflection (=15)

You must include a PDF file in your submission that identifies the grade you expect on this assignment based on what you completed. 

In addition, you will write 10 statements of either:
 - Accomplishments: things that you understand much better now.
 - Challenges: things that you are still working on understanding.

I will provide direct feedback to your writing.

### Program Compiles (=15)

This includes that your code should be professional. 
- Try to proofread your work like an essay! 
- Find all your ``println("stuff")`` statements and remove them, etc.
- Your code looks intentional: don't just fiddle with it until it works. Reason about it! Sketch out the python by hand and then try translating to Java.
- Delete commented out code or experiments that don't work out. Make your submission as small as it needs to be.
- Your code is your own. Respect the honor code.
- You will have a Buddy in Lab to work with through the first two tasks; the code that solves them is sufficiently simple that you do not need to cite, but put a comment in your ``Fish`` class indicating who your buddy was.
- Have comments explaining tricky code!

This section will expand in detail, importance and value as the semester goes on.

### Little Fish Moves (=5)
There's a little red fish in the starter code. Let's help that little fish out! They want to move.

### Fish Class (=15)
I expect you to develop and improve on a Fish class. I don't expect you to be able to do it without the following variables:

- int x, int y // Every fish should have its own position.
- Color color // Every fish should have its own color.
- // choose which method to call from DrawFish...Fish...()
- boolean facingLeft
- boolean isLittle

### Fish destination system (=16)

Consult the Submarine video on Moodle if stuck. The "Submarine Destination System" picks new points based on a user click, but is otherwise extremely similar.

- (2) A fish has a destination. 
- (4) It moves at a fixed speed toward that destination. There are many solutions to this problem. For the easiest, implement the GuessingGame if-statements for x and y. For the most difficult, you will need knowledge of Algebra/Trigonometry and you must use "double" values for position and speeds.
- (4) Your fish should face its destination. Set a member variable in your movement code. Do not duplicate your movement code.
- (4) A fish destination is chosen at random. (Hint, look at guessing-game for how to use Random!).
- (4) When it is close enough to the destination, it chooses another.

Once you fully understand the Fish destination system (or have solved all but the last bullet point, you're ready to look further at the ***Choose-your-own*** Goals.)

## Choose-your-own Goals:

You may complete any subset of tasks for these options, so long as you fully complete at least one Option.
***I strongly suggest you start by doing Option 1, the easiest, and move into the harder one of your choice.***

### Option 1: Bubble Class and Bubble Array (=15)

Consult the Waves video on Moodle if stuck. The Waves move horizontally rather than vertically but are otherwise extremely similar. This is one of the easier tasks, so it's worth slightly less.

- (3) You have a functioning Bubble object that drifts upward.
- (3) Your bubble reappears at the bottom of the tank after a delay.
- (3) You create a fixed bubble array of 10 bubbles. You may want a class ``BubbleSystem`` if you feel that your ``Aquarium`` class is getting too busy.
- (3) Bubbles are randomly sized, wiggle a little, etc.
- (3) Build a treasure-chest (or some other source of the bubbles).

### Option 2: HungryFish extends Fish (=20)

You can leave your fish from lab alone for now, and either create a subclass of it, or a whole new fish class, called HungryFish.

- (4) Food is visible in some part of the aquarium (it may be an area with kelp, seaweed, etc.)
- (4) When your fish is not in this area, it grows hungry.
- (4) Your fish picks destinations in the food zone when very hungry, and outside the zone when normal.
- (4) Display a hunger meter. When a fish is hungry, color it different, display a progress bar (6 points, not 4), or some other indicator to deal with it.
- (4) DIFFICULT! Fish food falls from above in the food zone, and a fish must stay there until food becomes visible (at which point their hunger goes to zero).

### Option 3: Algorithm, the Snail who loves Algae. (=20)

Algorithm is a snail that loves to eat algae. 

- (4) They sleep most of the time, and over time the tank gets greener. Hint: Just "animate" the green component of the ocean color. There is a constructor for Java's Color class that takes three parameters: ``(int r, int g, int b)``
- (4) They walk around the outside of the tank, upside-down if need be, since they stick to anything.
- (4) When it reaches a certain level, Algorithm wakes up, and starts eating the algae. Make their eyes open/close? (requires reading graphics code).
- (4) The water in the tank becomes more blue again.
- (4) When they get full, they fall back to sleep, wherever they are. So they must be moving!

### Option 4: Predator/Prey (=24)
 - (4) And lo, there was a Shark named Jaws. Or a Diver, named Frieda. Or some kind of predator.
 - (4) Your fish must be in an Array or List, as your predator hunts in a for loop.
 - (4) Your predator must have a similar destination system, except their destination is a particular Fish!
 - (4) When your predator reaches your fish (or close enough), the fish becomes eaten. This is sad, but life? :/
     - Your fish disappears?
     - Make sure your predator doesn't hunt a missing fish!
     - Your fish flips upside down and floats to the top?
 - (4) DIFFICULT! When your predator eats any fish, it scares the other fish. They should pick points far away. (Try corners that don't contain the predator?)
 - (4) DIFFICULT! When your predator eats all of the fish, it needs to have a dramatic exit, e.g.: to swim sadly off-screen. Then you can print "THE END."
 
 

