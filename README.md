# Deliverable 4: Performance testing

### Exploratory Testing

At size 10, the game performed as expected. Only the write function seemed a bit weird at times, sometimes it didn't successfully save the state of the system.

### Profiling with VisualVM

After using visualVM to profile the performance of the game, it turned out like this:
![Profile before](/profiles/CPUprofile_before.JPG)

As we can see, first, the method convertToInt in MainPanel is taking up a large aount of CPU time. Which is really unusual if it is a method that does exactly what its name says.

So I looked into the java file and found the method. It has a completely useless while loop that just makes a string of 1000 zeroes. And its input was already an int, so there was no need to "convert" anything.

Second, the runContinuous method was also taking up much time. It might be because it is supposed to be running continuously, but I still checked the method just in case.

I found a try-catch function that made the method sleep for 20 milliseconds and a useless for loop.

Then I also checked the methods backup and calculateNextIteration that were used in runContinuous and I found that In calculateNextIteration, it uses a for loop to do iterateCell and then uses setVisible, which uses another for loop, and it could possibly be a cause of time wasting as well.

I couldn't find anything on the write method using profiling, but I checked the code as well and found yet another useless for loop in the toString method of the Cell class.

### Refactoring methods

In the first problem with convertToInt, the change was simple. I deleted the while loop and returned whatever variable came in.

For the second problem, it was more complicated. For starters, the try-catch function and for loop looked time wasting so I deleted them. 

Then, in the calculateNextIteration method, I felt that there was no need for 2 for loops, and that there should be a way to put them into 1. The answer was using "\_backupCells".

Because with every iteration, the current state is always backed up first, we can use the backup for any calculation and change the current cells without worrying it will affect the calculation. So I changed all the "\_cells" used in iterateCell and its methods to "\_backupCells" and made a cell visible when it was calculated.

For the last problem, I deleted the for loop.

### Pinning tests

To test the first problem, I checked that ever int that went in the method came out the same. I used edge case like 0 and max integer, and a normal integer like 100.

To test the second problem, I used the 4 laws of Conway's Game of Life and made a test for each law. If the calculation for these four laws is correct, then the outcome of the cells state should be correct as well.

To test the last change, I made sure that the the method return the correct string for each state: alive, dead, and default.

All tests passed.

### Profiling after refactoring

After the changes, I ran the program with VisualVM again and this time it was like this:
![Profile after] (/profiles/CPUprofile_after.JPG)

We can see that the convertToInt was almost gone. The time for runContinuous also dropped considerably. The performance was slightly improved.
