# MCAPI
Minecraft Query&Rcon Api

## BUILD
- master : mvn clean package
- native : mvn clean -Pnative-image package

# 소스 출처
- Query : https://github.com/rmmccann/Minecraft-Status-Query

- Rcon : https://github.com/Kronos666/rkon-core

# 사용법

마인크래프트 서버와 다른 곳에서 실행시 application.yml mc.address에 서버 주소 입력 후 실행.

rcon이나 query port가 기본값이 아닐시에도 yml 변경.

실행 후 /swagger-ui.html로 접속하면 사용법 확인 가능.

기본 포트는 25585

# API
총 3개의 GET Method

## /fullstat
query fullstat 확인

## /basicstat
query basicstat 확인

## /rcon
원격 명령어 실행

### query parameter

password : rcon 비밀번호

command : 실행할 명령어

# Prometheus
## /metrics
online_player : 온라인 플레이어 인원수. type = gauge
