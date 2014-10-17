JSON-Quiz interpreter and provider
----------------------------------
This library interprets a JSON quiz and provides
the questions and answers through useful classes
and tools.

What adventages does this *platform* provide?
 * Questions are separated from groups of possible answers.
   You can reuse these grups when creating your quiz, so
   you can save repetitive typing for options.
 * There are complete Java objects for Questions and Answers.

JSON quiz format
----------------

This is the JSON quiz format that this platform is
able to interpret:

```
{"quiz": {
    "subject": "Cities and their countries",
    "description": "This quiz  cities to their countries.",
    "options": {
        "capitals": ["Caracas", "Rome", "Washington DC"],
        "countries": ["Italy", "United States", "Venezuela"]
    },

    "questions": [
        {"question": "What is the capital of the United States?",
            "options": "capitals", "answer": "Washington DC"},
        {"question": "Caracas city is the capital of which country?",
            "options": "countries", "answer": "Venezuela"},
        {"question": "Rome is the capital city of what country?",
            "options": "countries", "answer": "Italy"},
    ]
}}

```

You can find other examples of quizes in the `examples` directory.

Classes
-------
 - **Quiz** is the main class, an abstraction of the JSON
   file of the quiz. It is able to interpret a valid JSON quiz
   using the `org.json.*` library. Its interface is simple and intuitive.
 - **QuizAnswers** is an useful class for holding answers, given instance of *Quiz*.
 - **QuizTools** includes some tools:
 	- **void run(Quiz q)** is a simple Quiz interpreter an instance of *Quiz* using 
 	  a simple Quiz interpreter. It is useful for testing Quizes.
 	- **int getLetterOptionIndex()** is a safe way to get an answer user in console.
 	- **void main()** is an entry point for *run()*. The first parameter is the path
 	  to the JSON Quiz.