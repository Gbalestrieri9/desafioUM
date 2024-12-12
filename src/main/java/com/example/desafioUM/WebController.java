package com.example.desafioUM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {
    @Autowired
    ScoreRepository scoreRepository;

    @ResponseBody
    @GetMapping("/score")
    public Score getScore() {
        Score score;
        try {
            score = scoreRepository.findById(Integer.valueOf(1)).get();
        } catch (Exception e) {
            score = new Score(0, 0, 0);
            scoreRepository.save(score);
        }
        return score;
    }

    @GetMapping("/teste")
    public String teste(@RequestParam(name="escolha") String aEscolha, Model model) {
        String saida = "empate";
        Score score;

        try {
            score = scoreRepository.findById(Integer.valueOf(1)).get();
        } catch (Exception e) {
            score = new Score(0, 0, 0);
            scoreRepository.save(score);
        }

        if (aEscolha.equalsIgnoreCase("papel")) {
            saida = "ganhou";
            score.setVitorias(score.getVitorias() + 1);
        } else if (aEscolha.equalsIgnoreCase("tesoura")) {
            saida = "perdeu";
            score.setDerrotas(score.getDerrotas() + 1);
        } else {
            score.setEmpates(score.getEmpates() + 1);
        }

        scoreRepository.save(score);

        model.addAttribute("saida", saida);
        model.addAttribute("aEscolha", aEscolha);
        model.addAttribute("score", score);
        return "resultado";
    }

    @GetMapping("/reset")
    public String resetScore(Model model) {
        try {
            Score score = scoreRepository.findById(Integer.valueOf(1)).get();
            score.setVitorias(0);
            score.setDerrotas(0);
            score.setEmpates(0);
            scoreRepository.save(score);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("mensagem", "Erro ao resetar o score: " + e.getMessage());
            return "erro";
        }
    }
}