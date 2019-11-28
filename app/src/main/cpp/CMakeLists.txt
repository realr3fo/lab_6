cmake_minimum_required(VERSION 3.6.0)

add_library(helloworld
    SHARED

    helloworld.c)

# Include libraries needed for lib
target_link_libraries(helloworld
        android
        log)
