rem java -jar "C:\user\materias\SCE\tstRMI\dist\tstRMI.jar"
rem -Djava.rmi.server.hostname=dellbi
java -cp .;C:\user\materias\SCE\tstRMI\build\classes^
 -Djava.rmi.server.hostname=localhost^
 -Djava.rmi.server.codebase=file:C:/user/materias/SCE/tstRMI/build/classes/^
 -Djava.security.policy=server.policy^
 example.hello.Server



