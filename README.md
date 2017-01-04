## A Solver for the Bounded Post's Correspondence Problem 
The Bounded Post's Correspondence Problem (BPCP) is an important variant of the much-celebrated [Post's Correspondence Problem] (https://en.wikipedia.org/wiki/Post_correspondence_problem). Given a list of pairs of strings, is there a way to permute at most *k* pairs such that the resulting pair sequence contains identical strings? If there is a solution, it is returned as a sequence of numbers that correspond to the resulting permutation that represents a solution. The length of this sequence must necessarily be less than or equal to the specified value of *k*. In this way, the BCPC is *decidable*, unlike its parent, the PCP, which is undecidable.

## Problem Explanation 

In BPCP, the input is a list of pairs of strings, including an integer *k* that represents the maximum length of a potential solution sequence. Consider the example list of pairs below:

```
 a       ab      bba
---     ----    -----
baa      aa       bb
 
 1       2        3
```

In this case, we have three pairs, numbered 1, 2, and 3, respectively. If *k* >= 4, then there is a solution of the form:

```
bba      ab     bba     a
-----   ---    ----   ----
 bb      aa     bb     baa
 3       2       3      1
```

A quick look at the solution reveals that the above string is the same as the bottom string, which are both ```bbaabbbaa```. Correspondingly, the solution sequence is ```3 2 3 1```, whose length is 4. However, if *k* <= 3, then no solution exists.

## How to Run 

To run the program, execute the following command:

````
java -jar Runner.jar 
```

Then simply follow the on-screen instructions to begin. 

## A Word on Efficiency 

Although the BPCP is decidable, hard instances can still take a considerable amount of time to find a solution. As it currently stands, the program is unlikely to find solutions whose lengths are in excess of 50-60 pairs. For this reason, it is recommended that only easy-to-medium instances be used for it. 

## Help 

For questions or comments, please email me at amalghannam@crimson.ua.edu. 


