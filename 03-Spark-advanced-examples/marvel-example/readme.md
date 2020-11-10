

# Introduction

We have a social graph of superheroes.

There is a connection between two heroes if they appear together
in any comic book. 

Two files:

-   Marvel-graph.txt 
    It contains the social graph.
    It has the following pattern:
    
    -   Hero1 Hero2 Hero3 &#x2026; HeroN
    
    It means that Hero1 has appeared with Hero2, Hero2, HeroN

-   Marvel-names.txt
    It contains the mapping between a hero id and its actual name


# Exercise 1

Show each hero in the social in decreasing order according to his/her popularity.

The popularity is defined as the number co-appearences with any other hero.


# Exercise 2

Compute the degree of separation between two heroes.

Each line of the praph file must be converted as follows:

-   input: 342 1156 345 6430
-   output: (342, (1156, 345, 6430), 9999, WHITE)

What does the output mean?
The first value is the main hero &#x2013; the starting point &#x2013; 
the second value, the tuple, contains all the heroes 342 is connected to,
9999 is the degree of separation and the last value indicates 
whether or not 342 has been visited during the Breadth First Search.

The program needs as input the starting hero and the target hero and it
outputs the degree of separation &#x2013; the distance &#x2013; between these superheroes.

-   Hints: BFS in Scala

    -   Define a function `convertToBFSNode` that convert a line int a 
        `BFSNode` &#x2013; i.e., an object containing all the information for performing
        the Breadth First Algorithm
    
    -   Each iteration of the BFS algorithm involves a mapper and a reducer. 
        
        The mapper will create a BFS node for each Hero linked to the current one.
        Also, it colors the new node and increase the current distance from the 
        source node.
    
    -   After the map phase is done, the reducer will combine together all the nodes
        having the same hero ID.
    
    *When do we stop?*
    We need an `Accumulator`, which is an object shared among all the 
    cluster that keeps some variable that needs to be shared among all the nodes
    within the cluster.
    
    We will use this `Accumulator` to signal that target node
    has been found.

