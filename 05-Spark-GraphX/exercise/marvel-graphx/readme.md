

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

Load the content of these files in a Graph and try to solve the following problems.

# Exercise 1

Show each hero in the social in decreasing order according to his/her popularity.

The popularity is defined as the number co-appearences with any other hero.


# Exercise 2

Compute the degree of separation between two heroe via the Pregel API

