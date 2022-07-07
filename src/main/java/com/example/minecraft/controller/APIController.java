package com.example.minecraft.controller;

import com.example.minecraft.query.MCQuery;
import com.example.minecraft.rcon.Rcon;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Tag(name = "minecraft")
public class APIController {

    @Value("${mc.address}")
    private String address;

    @Value("${mc.query_port}")
    private int queryPort;

    @Value("${mc.rcon_port}")
    private int rconPort;

    @Operation(summary = "fullstat 조회", description = "fullstat 조회")
    @GetMapping("fullstat")
    public Object fullstat() {
        try {
            return new MCQuery(address, queryPort).fullStat();
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    @Operation(summary = "basicstat 조회", description = "basicstat 조회")
    @GetMapping("basicstat")
    public Object basicstat() {
        try {
            return new MCQuery(address, queryPort).basicStat();
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    @Operation(summary = "rcon", description = "원격 명령 실행.")
    @GetMapping("rcon")
    public String rcon(@Parameter(description = "RCON 비밀번호") String password,@Parameter(description = "실행할 명령어") String command) {
        String result;
        try {
            Rcon rcon = new Rcon(address, rconPort, password);
            result = rcon.command(command);
        } catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }

}
