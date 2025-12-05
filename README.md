# pocj8ur4in/file_extension_blocker

![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-brightgreen.svg)
![Gradle](https://img.shields.io/badge/Gradle-8.0+-02303A.svg?logo=gradle)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-005F0F.svg?logo=thymeleaf)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-316192.svg?logo=postgresql)

block or restrict attachment/upload of files based on file extension

## Features

### Fixed Extensions

1. Fixed extensions are a list of frequently blocked extensions, and are unchecked by default.
2. When fixed extensions are checked or unchecked, they are saved to the database. They should persist after refresh.
   - Note: They are not displayed in the custom extension section below.

### Custom Extensions

1. Maximum input length for extensions is 20 characters.
2. When the add button is clicked, it is saved to the database and displayed in the area below.
3. Custom extensions can be added up to a maximum of 200.
4. Clicking the X next to an extension deletes it from the database.
