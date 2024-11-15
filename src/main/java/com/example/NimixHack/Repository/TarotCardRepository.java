package com.example.NimixHack.Repository;

import com.example.NimixHack.Model.TarotCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarotCardRepository extends JpaRepository<TarotCard,Integer> {
}
