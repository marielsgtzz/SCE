set cb="tstRMI_proyecto.jar"

if [%1] NEQ [] goto conHost
java -Djava.rmi.server.codebase=file:///%cb% -jar %cb% Master localhost reporta
goto fin

:conHost
java -Djava.rmi.server.codebase=file:///%cb% -jar %cb% Master %1 reporta
:fin


