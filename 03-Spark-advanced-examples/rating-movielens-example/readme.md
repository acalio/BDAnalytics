# Introduction

You are required to build a spark-based program that is able to compute several
statics on the (famous) MovieLens dataset.

The dataset contains the information:

1.  User Id
2.  Movie Id
3.  Rating
4.  Timestamp

In the folder `data` you have two files:

1.  `u.data` &#x2013; it contains the following information:
    
    `userId` `movieId` `rating` `timestamp`

2.  `u.item`
    `movieId` | `movieName` | `movieData` | `movieUrl` | &#x2026; binary attributes &#x2026;


# First Example

Find the most popular movie.

In order to find it we only need to account the number of times it 
has been viewed by a person.

Therefore, you do not need to take into consideration the actual ratings.


# Second Example

Instead of displaying the id of a movie, let's derive
the actual name of each movie.

There is another dataset, `item.data` which contains the actual name 
for every movie id.

We have two options:

1.  Load the entire dataset into a map &#x2013; into a particular node
2.  Let Spark automatically distribute this map to all the executor nodes

Obviously, the second option is the one to be preferred &#x2013; in particular
when the data are huge.
with broadcast variable we are able to do so. 
We can explictly distribute a chunk of data along all the nodes in the 
cluster.


# Third Example: Item Based Collaborative Filtering


## Collaborative Filtering in a nutshell

-   We want to find similarities between items &#x2013; in this case movies.
-   Users behavior is exploited in  order to find similar movies. The basic assumptions is:
    *movies with similar ratings from the same user should be related to each other*.
-   Example: If many users rate  similarly the *Godfather* and the *Godfather part II* then the 
    algorithm assumes there is a connection between these movies.
-   For pair of movies (m<sub>1</sub>, m<sub>2</sub>) we have two vector of scores &#x2013; which are the scores provided
    by any user that rated both m<sub>1</sub> and m<sub>2</sub>
-   Once you obtained these vector, the similarity between each pair items is given by some
    similarity measure applied over the pair of vectors.


## Move the algorithm Spark

This is the plan (at a very high level).

-   Find every pair of movies that were watched by the same person
-   Measure the similarity of their ratings across all users that watched both
-   Sort by movie, then by similarity strength

How to do it in Spark?

-   Map the input ratings to obtain tuples of this form: (userId, (movieID, rating))
-   Find every movie-pair rated by the same user:
    -   This can be accomplished using `self-join` operation. 
        It will give tuples like: (userID, ((movieID1, rating1), (movieID2, rating2)))
-   Filter out duplicate pairs
-   Make the movie pairs the key. Therefore we will have tuples like: ((movieID1, movieID2), (rating1, rating2))
-   `groupByKey()` to get every rating pair found for each movie in the key-pair
-   Sort, save and display the results

The problem is pretty complex. If N is the number of movie-rating pars, we can end up with a final RDD which is
at most N times the original RDD.

For this reason, we will *cache* the intermediate results in order to prevent
Spark to re-evaluate the entire RDD several times.


## Improve the previous results

Here is a list of updates you may want to try:

-   Discard bad ratings &#x2013; only recommend good movies (with high rating)
-   Try different similarity metrics (e.g., Pearson Correlation, Jaccard, Conditional Probability)
-   Adjust the thresholds for minimum score and co-occurrences
-   Define some other metric that takes into account the number of common users between movies
-   Use genre information to boost scores from movies with the same genre

