#!/bin/bash

alias bd="cd /home/alejandro/Github/ResRst/Reservaciones/src/reservaciones"

run2(){
    program=$1
    cd ..
    javac reservaciones/$1
    if [ $? -eq 0 ]; then 
        java reservaciones.${program/.java/""}
        cd reservaciones
    fi 
    rm -f *.class   
}
