# Sprout
## Overview

The Sprout is a user-friendly application designed to help plant enthusiasts keep track of their plant collection. Whether you're a seasoned plant parent or just starting your green journey, this app provides a convenient way to manage and care for your plants.

## Frontend
For the application to work, the frontend must also be launched.

https://github.com/C00kier/Plant-app-frontend

## Demo
https://youtu.be/RRMcCNbmFWc

## Features

- Searching for Plant Details: Easily search for detailed information about specific plants. Access care tips, growth patterns, and more at your fingertips.

- Blog Articles: Stay informed and entertained with a curated collection of blog articles covering various topics related to plant care, gardening tips, and the latest trends in the plant community.

- Gamification: Make plant care fun by incorporating gamification elements. Earn badges, gain points, and set personal challenges to keep your plant care routine engaging.

- Recommendation Based on Quiz: Answer a few questions in the app's quiz, and receive personalized plant recommendations tailored to your lifestyle, environment, and preferences.

- Care System: Get reminder and manage watering, fertilizing, and repotting of your plants.

- Assigning Plants to Rooms: Organize your plants by assigning them to specific rooms in your home.

- Assigning Special Names to Plants: Add a personal touch by giving special names to your plants. Create a connection with your green companions and make plant care a more enjoyable experience.

## Application.properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/YOUR_DB_NAME
    spring.datasource.username=YOUR_USERNAME
    spring.datasource.password=YOUR_PASSWORD
    spring.datasource.driver-class-name=org.postgresql.Driver

    spring.jpa.defer-datasource-initialization=true
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true

    secret_key=YOUR_SECRET_KEY
    google_client_id=YOUR_GOOGLE_CLIENT_ID
    recaptcha.secret=YOUR_RECAPTCHA_SECRET

If you encounter any issues or have questions, please open an issue.

Happy planting! 🌱🌿
