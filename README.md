## A Solver for the Bounded Post's Correspondence Problem 
The Bounded Post's Correspondence Problem (BPCP) is an important variant of the much-celebrated [Post's Correspondence Problem] (https://en.wikipedia.org/wiki/Post_correspondence_problem). Given a list of pairs of strings, with each pair numbered in the order it appears, is there a way to permute at most *k* pairs such that the resulting pair sequence contains identical strings? 

If there is a solution, it is returned as a sequence of numbers corresponding to the resulting permutation that represents the solution. The length of this sequence must necessarily be less than or equal to the specified value of *k*. In this way, the BPCP is *decidable*, unlike its parent, the PCP, which is undecidable.

## Example Instance 

In the BPCP, the input is a list of pairs of strings, along with an integer *k* (which must be larger than zero!) that represents the maximum possible length of a potential solution sequence. Consider the example list of pairs below:

```
 a       ab      bba
---     ----    -----
baa      aa       bb
 
 1       2        3
```

In this case, we have three pairs, numbered 1, 2, and 3, respectively. If *k* >= 4, then there is a solution of the form:

```
 bba     ab     bba     a
-----   ---    ----   ----
 bb      aa     bb     baa
 3       2       3      1
```

A quick look at the solution reveals that the top string is the same as the bottom string, which are both ```bbaabbbaa```. Correspondingly, the solution sequence is ```3 2 3 1```, whose length is 4. However, if *k* <= 3, then no solution exists, since no matter how we try to permute the pairs, it is impossible to find a matching sequence using only three or fewer pairs. 

## How to Run 

To run the program, export it to a JAR and then execute the following command in the directory where the ```.jar``` file resides:

````
> java -jar Runner.jar 
```

Then simply follow the on-screen instructions to begin. Alternatively, it can be run directly from your IDE if you simply import it. 

## A Word on Efficiency 

Post's Correspondence Problem is a *hard* computational problem and so are its variants. Although the BPCP is decidable, hard instances will still take a considerable amount of time to find a solution for. As it currently stands, this program is unlikely to efficiently find solutions whose lengths are in excess of ~40-60 pairs. For this reason, it is recommended that only easy-to-medium instances be used for it. 

## Help 

For questions or comments, please email me at amalghannam@crimson.ua.edu. 


