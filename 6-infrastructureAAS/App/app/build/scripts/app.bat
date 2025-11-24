@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem
@rem SPDX-License-Identifier: Apache-2.0
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  app startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and APP_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\app.jar;%APP_HOME%\lib\basyx.components.registry-1.4.0.jar;%APP_HOME%\lib\basyx.components.AASServer-1.4.0.jar;%APP_HOME%\lib\basyx.components.lib-1.4.0.jar;%APP_HOME%\lib\basyx.sdk-1.4.0.jar;%APP_HOME%\lib\keycloak-admin-client-19.0.3.jar;%APP_HOME%\lib\resteasy-jackson2-provider-3.13.2.Final.jar;%APP_HOME%\lib\jackson-jaxrs-json-provider-2.14.2.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.14.2.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.14.2.jar;%APP_HOME%\lib\jackson-core-2.14.2.jar;%APP_HOME%\lib\jackson-annotations-2.14.2.jar;%APP_HOME%\lib\json-patch-1.9.jar;%APP_HOME%\lib\jackson-coreutils-1.6.jar;%APP_HOME%\lib\jackson-databind-2.14.2.jar;%APP_HOME%\lib\jsch-0.1.55.jar;%APP_HOME%\lib\fluent-hc-4.5.14.jar;%APP_HOME%\lib\logback-classic-1.4.6.jar;%APP_HOME%\lib\sdk-client-0.6.8.jar;%APP_HOME%\lib\sdk-server-0.6.8.jar;%APP_HOME%\lib\javax.servlet-api-4.0.1.jar;%APP_HOME%\lib\javax.ws.rs-api-2.1.1.jar;%APP_HOME%\lib\jersey-client-2.39.1.jar;%APP_HOME%\lib\jersey-hk2-2.39.1.jar;%APP_HOME%\lib\tomcat-catalina-9.0.73.jar;%APP_HOME%\lib\poi-ooxml-4.1.2.jar;%APP_HOME%\lib\janino-3.1.9.jar;%APP_HOME%\lib\org.eclipse.paho.client.mqttv3-1.2.5.jar;%APP_HOME%\lib\gson-2.10.1.jar;%APP_HOME%\lib\commons-fileupload-1.5.jar;%APP_HOME%\lib\tika-core-2.7.0.jar;%APP_HOME%\lib\resteasy-multipart-provider-3.13.2.Final.jar;%APP_HOME%\lib\resteasy-client-3.13.2.Final.jar;%APP_HOME%\lib\resteasy-jaxrs-3.13.2.Final.jar;%APP_HOME%\lib\commons-io-2.11.0.jar;%APP_HOME%\lib\httpmime-4.5.14.jar;%APP_HOME%\lib\httpclient-4.5.14.jar;%APP_HOME%\lib\commons-lang3-3.12.0.jar;%APP_HOME%\lib\spring-security-oauth2-resource-server-5.8.2.jar;%APP_HOME%\lib\spring-security-oauth2-jose-5.8.2.jar;%APP_HOME%\lib\mongodb-driver-sync-4.9.0.jar;%APP_HOME%\lib\spring-data-mongodb-3.4.10.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\logback-core-1.4.6.jar;%APP_HOME%\lib\stack-client-0.6.8.jar;%APP_HOME%\lib\sdk-core-0.6.8.jar;%APP_HOME%\lib\stack-server-0.6.8.jar;%APP_HOME%\lib\bsd-generator-0.6.8.jar;%APP_HOME%\lib\bsd-core-0.6.8.jar;%APP_HOME%\lib\stack-core-0.6.8.jar;%APP_HOME%\lib\spring-data-commons-2.7.10.jar;%APP_HOME%\lib\HikariCP-3.4.5.jar;%APP_HOME%\lib\camunda-engine-7.18.0.jar;%APP_HOME%\lib\camunda-engine-dmn-7.18.0.jar;%APP_HOME%\lib\camunda-engine-feel-juel-7.18.0.jar;%APP_HOME%\lib\camunda-engine-feel-scala-7.18.0.jar;%APP_HOME%\lib\camunda-engine-feel-api-7.18.0.jar;%APP_HOME%\lib\camunda-commons-typed-values-7.18.0.jar;%APP_HOME%\lib\camunda-connect-connectors-all-1.5.6.jar;%APP_HOME%\lib\camunda-connect-core-1.5.6.jar;%APP_HOME%\lib\camunda-commons-utils-1.12.0.jar;%APP_HOME%\lib\camunda-commons-logging-1.12.0.jar;%APP_HOME%\lib\feel-engine-1.14.2-scala-shaded.jar;%APP_HOME%\lib\slf4j-api-2.0.6.jar;%APP_HOME%\lib\jersey-common-2.39.1.jar;%APP_HOME%\lib\jakarta.ws.rs-api-2.1.6.jar;%APP_HOME%\lib\hk2-locator-2.6.1.jar;%APP_HOME%\lib\hk2-api-2.6.1.jar;%APP_HOME%\lib\hk2-utils-2.6.1.jar;%APP_HOME%\lib\jakarta.inject-2.6.1.jar;%APP_HOME%\lib\javassist-3.29.0-GA.jar;%APP_HOME%\lib\tomcat-jsp-api-9.0.73.jar;%APP_HOME%\lib\tomcat-util-scan-9.0.73.jar;%APP_HOME%\lib\tomcat-api-9.0.73.jar;%APP_HOME%\lib\tomcat-coyote-9.0.73.jar;%APP_HOME%\lib\tomcat-servlet-api-9.0.73.jar;%APP_HOME%\lib\tomcat-util-9.0.73.jar;%APP_HOME%\lib\tomcat-juli-9.0.73.jar;%APP_HOME%\lib\tomcat-annotations-api-9.0.73.jar;%APP_HOME%\lib\tomcat-jni-9.0.73.jar;%APP_HOME%\lib\tomcat-jaspic-api-9.0.73.jar;%APP_HOME%\lib\poi-4.1.2.jar;%APP_HOME%\lib\poi-ooxml-schemas-4.1.2.jar;%APP_HOME%\lib\commons-compress-1.19.jar;%APP_HOME%\lib\curvesapi-1.06.jar;%APP_HOME%\lib\commons-compiler-3.1.9.jar;%APP_HOME%\lib\httpcore-4.4.16.jar;%APP_HOME%\lib\commons-codec-1.13.jar;%APP_HOME%\lib\spring-security-oauth2-core-5.8.2.jar;%APP_HOME%\lib\spring-security-web-5.8.2.jar;%APP_HOME%\lib\spring-security-core-5.8.2.jar;%APP_HOME%\lib\spring-tx-5.3.26.jar;%APP_HOME%\lib\spring-context-5.3.26.jar;%APP_HOME%\lib\spring-web-5.3.25.jar;%APP_HOME%\lib\spring-aop-5.3.26.jar;%APP_HOME%\lib\spring-beans-5.3.26.jar;%APP_HOME%\lib\spring-expression-5.3.26.jar;%APP_HOME%\lib\spring-core-5.3.26.jar;%APP_HOME%\lib\nimbus-jose-jwt-9.24.4.jar;%APP_HOME%\lib\keycloak-core-19.0.3.jar;%APP_HOME%\lib\keycloak-common-19.0.3.jar;%APP_HOME%\lib\resteasy-jaxb-provider-3.13.2.Final.jar;%APP_HOME%\lib\mongodb-driver-core-4.9.0.jar;%APP_HOME%\lib\bson-record-codec-4.9.0.jar;%APP_HOME%\lib\bson-4.9.0.jar;%APP_HOME%\lib\postgresql-42.6.0.jar;%APP_HOME%\lib\camunda-bpmn-model-7.18.0.jar;%APP_HOME%\lib\h2-2.1.214.jar;%APP_HOME%\lib\netty-channel-fsm-0.8.jar;%APP_HOME%\lib\netty-codec-http-4.1.77.Final.jar;%APP_HOME%\lib\bcpkix-jdk15on-1.69.jar;%APP_HOME%\lib\bcutil-jdk15on-1.69.jar;%APP_HOME%\lib\bcprov-jdk15on-1.69.jar;%APP_HOME%\lib\guava-31.0.1-jre.jar;%APP_HOME%\lib\netty-handler-4.1.77.Final.jar;%APP_HOME%\lib\netty-codec-4.1.77.Final.jar;%APP_HOME%\lib\jaxb-runtime-2.3.6.jar;%APP_HOME%\lib\jakarta.annotation-api-1.3.5.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.3.jar;%APP_HOME%\lib\aopalliance-repackaged-2.6.1.jar;%APP_HOME%\lib\tomcat-el-api-9.0.73.jar;%APP_HOME%\lib\commons-collections4-4.4.jar;%APP_HOME%\lib\commons-math3-3.6.1.jar;%APP_HOME%\lib\SparseBitSet-1.2.jar;%APP_HOME%\lib\xmlbeans-3.1.0.jar;%APP_HOME%\lib\spring-security-crypto-5.8.2.jar;%APP_HOME%\lib\jcip-annotations-1.0-1.jar;%APP_HOME%\lib\jboss-jaxrs-api_2.1_spec-2.0.1.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\jakarta.mail-1.6.5.jar;%APP_HOME%\lib\apache-mime4j-0.6.jar;%APP_HOME%\lib\jboss-jaxb-api_2.3_spec-2.0.0.Final.jar;%APP_HOME%\lib\spring-jcl-5.3.26.jar;%APP_HOME%\lib\checker-qual-3.31.0.jar;%APP_HOME%\lib\camunda-cmmn-model-7.18.0.jar;%APP_HOME%\lib\mybatis-3.5.6.jar;%APP_HOME%\lib\joda-time-2.1.jar;%APP_HOME%\lib\camunda-dmn-model-7.18.0.jar;%APP_HOME%\lib\camunda-xml-model-7.18.0.jar;%APP_HOME%\lib\strict-machine-0.6.jar;%APP_HOME%\lib\netty-transport-4.1.77.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.77.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.77.Final.jar;%APP_HOME%\lib\netty-common-4.1.77.Final.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\jakarta.xml.bind-api-2.3.3.jar;%APP_HOME%\lib\txw2-2.3.6.jar;%APP_HOME%\lib\istack-commons-runtime-3.0.12.jar;%APP_HOME%\lib\jakarta.activation-1.2.2.jar;%APP_HOME%\lib\reactive-streams-1.0.3.jar;%APP_HOME%\lib\jakarta.validation-api-2.0.2.jar;%APP_HOME%\lib\jboss-annotations-api_1.3_spec-2.0.1.Final.jar;%APP_HOME%\lib\msg-simple-1.1.jar;%APP_HOME%\lib\jakarta.activation-api-1.2.2.jar;%APP_HOME%\lib\btf-1.2.jar;%APP_HOME%\lib\error_prone_annotations-2.7.1.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar


@rem Execute app
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %APP_OPTS%  -classpath "%CLASSPATH%" ovs_aas.App %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable APP_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%APP_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
