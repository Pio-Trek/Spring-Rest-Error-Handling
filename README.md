# Spring-Rest-Error-Handling

To create a custom error handling exception, we need to create a class that will inherit from **RuntimeException.**

AuthorNotFoundException.java

    @ResponseStatus(HttpStatus.NOT_FOUND)

    public class AuthorNotFoundException extends RuntimeException {

        public AuthorNotFoundException(String message) {

            super(message);

        }

    }

We can override various of different constructors from the parent class. In this example, I'm using construction that accepts a message as a String parameter.

Spring will generate a default JSON error response like below:

    {

        "timestamp": "2020-01-18T17:06:27.639+0000",

        "status": 404,

        "error": "Not Found",

        "message": "No author found here...",

        "path": "/api/authors/5"

    }

To provide more customization for the output we need to set up a POJO class that will model a message and a class that will handle all exceptions with **@ControllerAdvice** which is a kind of 

an interceptor for exceptions.

CustomErrorRespone.java

    @Getter

    @Builder

    public class CustomErrorResponse {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")

        private LocalDateTime timestamp;

        private int status;

        private String error;

    }

CustomGlobalExceptionHandler.java

    @ControllerAdvice

    @Slf4j

    public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler({BookNotFoundException.class})

        public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex) {

            CustomErrorResponse error = CustomErrorResponse.builder()

                    .timestamp(LocalDateTime.now())

                    .error(ex.getMessage())

                    .status(HttpStatus.NOT_FOUND.value())

                    .build();

            log.error("Exception : " + ex.getMessage());

            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        }

The final output:

    {

        "timestamp": "2020-01-18 06:17:49",

        "status": 404,

        "error": "No nook found here..."

    }

For some reasons, we wish to have a hardcoded error message. We can use a **reason** property in **@ResponseStatus** annotation.

FetchAllInternalException.java

 

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Could not process the request. Please try again later.")

    public class FetchAllInternalException extends RuntimeException {

        public FetchAllInternalException() {

        }

    }

**👍 Pro-tip: move all error messages to some constants/enum class.**
