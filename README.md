# Unobtrusive JavaScript and Server-Generated JavaScript Response

Here is an attempt to use Rails-style unobtrusive JavaScript (UJS) adapter for JQuery and server-generated JavaScript responses (SJRs) in a Spring Boot web application.

## Server-Generated JS Response

To use a JSP that generates JavaScript, apart from the `InternalResourceViewResolver` bean named `defaultViewResolver` configured by Spring Boot's `WebMvcAutoConfiguration`, an additional `InternalResourceViewResolver` bean with `text/javascript` as content type is configured.
