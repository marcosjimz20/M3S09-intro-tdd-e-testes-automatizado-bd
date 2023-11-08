package br.lab365.labcar.fixtures;

import br.lab365.labcar.models.CarroModel;
import net.datafaker.Faker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CarroFixture {

    private static final Faker faker = new Faker(new Locale("pt-BR"));
    public static List<CarroModel> criarCarrosValidos(Integer qtd) {
        List<CarroModel> carros = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            carros.add(criarCarroValido());
        }
        return carros;
    }

    public static CarroModel criarCarroValido() {
        CarroModel carro = new CarroModel();
        carro.setMarca(faker.vehicle().manufacturer());
        carro.setModelo(faker.vehicle().model());
        carro.setAno(faker.date().birthday(0, 25).toLocalDateTime().getYear());
        carro.setPreco(new BigDecimal(faker.number().numberBetween(4, 38) * 1000));
        carro.setFoto(faker.name().title());

        return carro;
    }
}
