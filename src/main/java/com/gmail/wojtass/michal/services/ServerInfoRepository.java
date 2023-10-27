package com.gmail.wojtass.michal.services;

import com.gmail.wojtass.michal.model.ServerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerInfoRepository extends JpaRepository<ServerInfo,Long> {

    ServerInfo findByServerInfoId(long id);
}
