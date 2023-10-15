#!/usr/bin/env groovy
pipeline {
    agent any
    tools {
        maven 'myMaven'
        jdk 'jdk'
        
    }
    environment {
        registry = "bougarrani/demo-app:1.0"
               
    }
    stages {
        stage('install dependencies') {
            steps {
                script {
                   cd './clients'
                   sh 'mvn clean install'
                    cd '../amqp'
                     sh 'mvn clean install'
                }
            }
        }
        stage('Test Microservice Products') {
            steps {
               script {
                     echo 'testing microservice products...'
                     sh 'cd ./Products && mvn test && cd ..'
                 }
                  
               }
            }
        stage('Test Microservice Orders') {
            steps {
               script {
                     echo 'testing microservice orders...'
                     sh 'cd ./Orders && mvn test && cd ..'
                 }
                  
               }
            }
        stage('Test Microservice Identity') {
            steps {
               script {
                     echo 'testing microservice identity...'
                     sh 'cd ./identity_service && mvn test && cd ..'
                 }
                  
               }
            }
        
        // stage('build images') {
        //     steps {
        //         script {
        //             echo 'building docker images...'
        //             // building images using spring-boot-maven-plugin of microservices 
        //             sh 'cd ./Eureka_server && mvn spring-boot:build-image && cd ..'
        //             sh 'cd ./Notification && mvn spring-boot:build-image && cd ..'
        //             sh 'cd ./Gateway && mvn spring-boot:build-image && cd ..'
        //             sh 'cd ./identity_service && mvn spring-boot:build-image && cd ..'
        //             sh 'cd ./Products && mvn spring-boot:build-image && cd ..'
        //             sh 'cd ./Ordes && mvn spring-boot:build-image && cd ..'

        //             }
                    
                  
        //         }
        //     }
        // }
        // stage('push images') {
        //     steps {
        //         script {
        //             echo 'pushing docker images...'
        //             sh 'docker login -u bougarrani -p $DOCKER_PASSWORD'
        //             sh 'docker push bougarrani/eureka_server:1.0'
        //             sh 'docker push bougarrani/notification:1.0'
        //             sh 'docker push bougarrani/gateway:1.0'
        //             sh 'docker push bougarrani/identity_service:1.0'
        //             sh 'docker push bougarrani/products:1.0'
        //             sh 'docker push bougarrani/orders:1.0'
        //         }
        //     }
        // }
        // stage('deploy') {
        //     steps {
        //         script {
        //             def dockerCmd = "bash ./server-cmds.sh ${registry}"
        //             def ec2Instance = "ec2-user@35.180.64.17"
        //            sshagent(['ec2-server-key']) {
        //                     sh "scp server-cmds.sh ${ec2Instance}:/home/ec2-user"
        //                     sh "scp docker-compose.yaml ${ec2Instance}:/home/ec2-user"                      
        //                     sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${dockerCmd}"
                            
        //             }
        //         }
        //     }
        // }
    }

}