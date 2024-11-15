package com.example.NimixHack.Repository;

import com.example.NimixHack.Model.team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface teamRepository  extends JpaRepository<team,Integer> {
}
