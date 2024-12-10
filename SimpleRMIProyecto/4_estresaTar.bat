echo off
echo ejecuta el estresador para sembrar los clientes
echo uso:
echo 4_estresa NumCltes NumSolicitudesPorClte HOSTNAME (en caso de omitirlo se usa localhost, si se omiten los clientes usa 15 y el localhost)
echo on

rem =============================================================
rem 	definición de roles de los jars para la ejecución
rem =============================================================

set cb=%cd%\tstRMI_proyecto.jar
set JAR_INTERFAZ=InterfazVVBpel.jar
set JAR_CLTE_ESTRESS=CltePojoVinilesVintage.jar
set POJO_ESTRESS=Clt_proy_des
set DIST=proyectormi.Distribuidor

rem ===========================================================
rem	consolidación del classpath
rem ===========================================================

set clpt=%cb%;%JAR_INTERFAZ%;%JAR_CLTE_ESTRESS%

if [%1] NEQ [] goto conclientes
estresador 4 -Djava.rmi.server.codebase=file:%cb% -cp %clpt% %Dist% %POJO_ESTRESS%  localhost 10
goto fin

:conclientes

if [%2] NEQ [] goto consolicitudes
estresador %1 -Djava.rmi.server.codebase=file:%cb% -cp %clpt% %Dist% %POJO_ESTRESS%  localhost 10
goto fin

:consolicitudes
if [%3] NEQ [] goto conHost
estresador %1 -Djava.rmi.server.codebase=file:%cb% -cp %clpt% %Dist% %POJO_ESTRESS%  localhost %2
goto fin

:conHost
estresador %1 -Djava.rmi.server.codebase=file:%cb% -cp %clpt% %Dist% %POJO_ESTRESS% %3 %2


:fin