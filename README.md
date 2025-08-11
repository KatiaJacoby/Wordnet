Overview:

NGordnet is a Java project developed for UC Berkeley’s CS61B course. It provides tools to analyze historical word usage data over time using the Google Books Ngram dataset and WordNet lexical database. The project enables querying trends in word frequencies, synonyms, and related concepts across centuries.

Features:

- Historical Frequency Analysis: Query the relative frequency of words or groups of words across specified year ranges.
- Synonym and Hyponym Exploration: Utilize WordNet to find synonyms, hyponyms, and related words to broaden search queries.
- Data Structures Practice: Implement efficient data structures and algorithms for handling large datasets.
- Customizable Queries: Support complex queries combining sets of words and temporal constraints.
- JUnit Tests: Comprehensive unit tests validate functionality and edge cases.

Data Sources:

- Google Books Ngram Dataset: Contains word frequency counts for millions of words and phrases from 1500 to 2008.
- WordNet Lexical Database: Provides structured relationships between words (synonyms, hyponyms, hypernyms).

Usage:

1. Setup: Clone the repository and import the project into your Java IDE.
2. Data Files: Download the required data files (ngordnet.gz, words.txt, hyponyms.txt) from the course resources or instructor.
3. Run Queries: Use the provided API to perform queries on word frequencies, historical trends, and word relations.

Example:

  NgordnetQuery q = new NgordnetQuery("dog", 1950, 2000);
  double freq = wordHistory.wordFrequency(q.getWord(), q.getStartYear(), q.getEndYear());
  System.out.println("Frequency of 'dog' from 1950-2000: " + freq);


Testing:

- Run JUnit tests included in the tests directory to verify correctness.
- Tests cover frequency lookups, hyponym resolution, data loading, and edge cases.

Project Structure:

- ngordnet — main package with core classes for queries and data handling.
- tests — JUnit test cases.
- data — input files for word frequency and hyponym data.
