#!/bin/bash

alias bd="cd /home/alejandro/Github/ResRst/Reservaciones/src/reservaciones"

run2(){
    program=$1
    cd ..
    javac reservaciones/$1
    java reservaciones.${program/.java/""}
    cd reservaciones
    rm reservaciones/*.class    
}
