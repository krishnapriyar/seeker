# Seeker

App developed for Seek Interview

## Issues
**The provided backend is not able to be run on my machine (Mac OS Monterey, Apple Silicon) therefore it has impeded the proper development of a working end-to-end solution.**


I have raised this issue twice. I have done my best to develop the UI using some mock data.

## Developed Features
#### Login
#### Logout
#### Job List
#### My Applications [Partial]

## What to improve
- Given a working backend, write fully functional features
- Write unit tests

## App Architecture

The app uses a single activity architecture. All fragments of the app are hosted on MainActivity.

### Interactions 

[UI: Activity & Fragments] <-> Viewmodel -> Repository -> [Datasource: Local Storage / Network service]

## External Libraries 
- [Apollo](https://www.apollographql.com/docs/kotlin/tutorial/00-introduction/) for making GraphQL queries
- [Hilt](https://dagger.dev/hilt/) for dependency injection

