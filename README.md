# Student Score Processor (Java)

A Java program for calculating student scores, averages, and rankings from CSV files.

## Features
- Handles missing scores
- Calculates subtotal and average
- Classifies students into levels (A–F)
- Ranks students by total scores
- Outputs to `.txt` file and terminal

## Input Format (CSV)

Example input:
```
Name,Score1,Score2,Score3
Alice,90,85,88
Bob,78,,92
Charlie,100,95,
```

## Sample Output

```
Student Name    Score 1  Score 2  Score 3  Sub_Total  Average  Level  Rank
---------------------------------------------------------------------------  
Alice           90       85       88       263        87.67    B      2  
Bob             78                92       170        85.00    B      3  
Charlie         100      95                195        97.50    A      1
```

> Full input and output available in `Student_Scores_with_Empty_Columns.csv` and `Student_Scores3.txt`.

## Author
Benson Tsao  
GitHub Repository: [StudentScoreProcessor](https://github.com/BensonTsao/StudentScoreProcessor-)
