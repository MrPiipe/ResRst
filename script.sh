#!/bin/bash

alias bd="cd /home/alejandro/Github/ResRst/Reservaciones/src/reservaciones"

run2(){
    program=$1
    cd ..
    javac reservaciones/$1
    cd reservaciones
    mv *.class ../../build/classes/reservaciones
    cd ../../build/classes   
    java reservaciones.${program/.java/""}
    bd
}
