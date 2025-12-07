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

## Considerations

- **Duplicate Extension Check**
  - Prevents adding duplicate custom extensions

- **Fixed Extension Conflict Prevention**
  - Prevents adding custom extensions that already exist in fixed extensions

- **Case-Insensitive Processing**
  - All extensions are converted to lowercase before storage

- **Whitespace Removal**
  - Leading and trailing whitespace are automatically removed using `trim()`
  - Empty string validation after trimming

- **Extension Format Validation**
  - Only alphanumeric characters (a-z, A-Z, 0-9) are allowed
  - Special characters, dots, spaces, and non-ASCII characters are rejected
  - Validation at both DTO level (`@Pattern`) and service level

- **Transaction Management**
  - `@Transactional` annotations ensure data consistency
  - Read-only transactions (`@Transactional(readOnly = true)`) for query operations
  - Optimized performance with proper transaction boundaries

- **Maximum Count Limit Validation**
  - Server-side validation to prevent exceeding 200 custom extensions
  - **Pessimistic Lock Implementation**: Prevent race conditions
  - Ensures that concurrent requests cannot bypass the 200-item limit

- **UUID-Based Identifiers**
  - Each extension uses a UUID instead of sequential IDs
  - Enhances security by preventing ID enumeration attacks
  - UUIDs are automatically generated on entity creation
