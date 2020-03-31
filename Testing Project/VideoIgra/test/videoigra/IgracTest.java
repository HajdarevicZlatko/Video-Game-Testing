package videoigra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IgracTest {

    Oruzje oruzje = Mockito.mock(Oruzje.class);
    Odeca odeca = Mockito.mock(Odeca.class);
    Magija magija = Mockito.mock(Magija.class);

    //Zbog velikog broja testova koji moraju da se sprovedu, koristiće se parametarizovani testovi
    public static Stream<Arguments> returnValuesOfOdmoriSe() {
        return Stream.of(
                Arguments.of(76.0, 0.0),
                Arguments.of(75.0, 0.0),
                Arguments.of(74.0, 26.0),
                Arguments.of(60.0, 40.0),
                Arguments.of(51.0, 49.0),
                Arguments.of(50.0, 50.0),
                Arguments.of(49.0, 50.0),
                Arguments.of(30.0, 50.0)
        );
    }

    public static Stream<Arguments> getParametersForNapadniIgraca() {
        return Stream.of(
                //Arguments.of("napadniIgraca_ShouldReturn0_IfEnergijaIsLessThan21", 10.0, 10.0, 20.0, 20, 0.0, Igrac.Stanje.DEFANZIVNO),

                Arguments.of("napadniIgraca_ShouldReturnAnAdequateResultAndReduceEnergijaBy21_" +
                                "IfEnergijaIsGreaterThan21AndSnagaIsGreaterThanPotrebnaSnaga_WhenStanjeIsDEFANZIVNO"
                        , 10.0, 10.0, 21, 20, 40, Igrac.Stanje.DEFANZIVNO),
                Arguments.of("napadniIgraca_ShouldReturnAnAdequateResultAndReduceEnergijaBy21_" +
                                "IfEnergijaIsGreaterThan21AndSnagaIsEqualToPotrebnaSnaga_WhenStanjeIsDEFANZIVNO"
                        , 10.0, 10.0, 21, 10, 24, Igrac.Stanje.DEFANZIVNO),
                Arguments.of("napadniIgraca_ShouldReturnAnAdequateResultAndReduceEnergijaBy21_" +
                                "IfEnergijaIsGreaterThan21AndSnagaIsGreaterThanPotrebnaSnaga_WhenStanjeIsPASIVNO"
                        , 10.0, 10.0, 21, 20, 50, Igrac.Stanje.PASIVNO),
                Arguments.of("napadniIgraca_ShouldReturnAnAdequateResultAndReduceEnergijaBy21_" +
                                "IfEnergijaIsGreaterThan21AndSnagaIsGreaterThanPotrebnaSnaga_WhenStanjeIsAGRESIVNO"
                        , 10.0, 10.0, 21, 20, 60, Igrac.Stanje.AGRESIVNO),

                Arguments.of("napadniIgraca_ShouldReturnAnAdequateResultAndReduceEnergijaBy21_" +
                                "IfEnergijaIsGreaterThan21AndSnagaIsLessThanPotrebnaSnaga_WhenStanjeIsDEFANZIVNO"
                        , 10.0, 10.0, 21, 5, 12, Igrac.Stanje.DEFANZIVNO),
                Arguments.of("napadniIgraca_ShouldReturnAnAdequateResultAndReduceEnergijaBy21_" +
                                "IfEnergijaIsGreaterThan21AndSnagaIsLessThanPotrebnaSnaga_WhenStanjeIsPASIVNO"
                        , 10.0, 10.0, 21, 5, 15, Igrac.Stanje.PASIVNO),
                Arguments.of("napadniIgraca_ShouldReturnAnAdequateResultAndReduceEnergijaBy21_" +
                                "IfEnergijaIsGreaterThan21AndSnagaIsLessThanPotrebnaSnaga_WhenStanjeIsAGRESIVNO"
                        , 10.0, 10.0, 21, 5, 18, Igrac.Stanje.AGRESIVNO)
        );
    }

    static Stream<Arguments> getParametersForOdbraniSe() {
        return Stream.of(
                Arguments.of("odbraniSe_ShouldReturnAnAdequateResult_" +
                                "IfMaxTezinaIsGreaterThanTezinaOpreme_WhenIgracStanjeIsDEFANZIVNO"
                        , 100.0, 100, 10.0, 10.0, Igrac.Stanje.DEFANZIVNO, 1.6666),
                Arguments.of("odbraniSe_ShouldReturnAnAdequateResult_" +
                                "IfMaxTezinaIsGreaterThanTezinaOpreme_WhenIgracStanjeIsPASIVNO"
                        , 100.0, 100, 10.0, 10.0, Igrac.Stanje.PASIVNO, 2.5),
                Arguments.of("odbraniSe_ShouldReturnAnAdequateResult_" +
                                "IfMaxTezinaIsGreaterThanTezinaOpreme_WhenIgracStanjeIsAGRESIVNO"
                        , 100.0, 100, 10.0, 10.0, Igrac.Stanje.AGRESIVNO, 5),

                //Arguments.of("odbraniSe_ShouldReturnAnAdequateResult_
                // IfMaxTezinaIsEqualToTezinaOpreme_WhenIgracStanjeIsAGRESIVNO"
                // , 100.0, 10, 30.0, 10.0, Igrac.Stanje.AGRESIVNO, 1.6666),

                Arguments.of("odbraniSe_ShouldReturnAnAdequateResult_" +
                                "IfMaxTezinaIsLessThanTezinaOpreme_WhenIgracStanjeIsDEFANZIVNO"
                        , 21.0, 5, 60.0, 10.0, Igrac.Stanje.DEFANZIVNO, 3.3333),
                Arguments.of("odbraniSe_ShouldReturnAnAdequateResult_" +
                                "IfMaxTezinaIsLessThanTezinaOpreme_WhenIgracStanjeIsPASIVNO"
                        , 21.0, 5, 60.0, 10.0, Igrac.Stanje.PASIVNO, 5.5555),
                Arguments.of("odbraniSe_ShouldReturnAnAdequateResult_" +
                                "IfMaxTezinaIsLessThanTezinaOpreme_WhenIgracStanjeIsAGRESIVNO"
                        , 21.0, 5, 60.0, 10.0, Igrac.Stanje.AGRESIVNO, 10.416)
        );
    }

    static Stream<Arguments> getParametersForUpotrebiMagiju() {
        return Stream.of(
                Arguments.of("upotrebiMagiju_ShouldReturnAnAdequateResult_" +
                                "IfInteligencijaIsGreaterThanPotrebnaInteligencija_AndEnergijaIsGreaterThanPotrebnaEnergija_" +
                                "WhenZdravljeIsGreaterThanPotrebnaEnergija"
                        , /* potrebnaEnergija: */ 10.0, /* potrebnaInteligencija: */ 10.0, /* steta: */ 10.0,
                        /* zdravlje: */ 100.0, /* energija: */ 100.0, /* inteligencija: */ 11,
                        /* expectedResult: */ 81, /* expectedZdravlje: */ 100.0, /* expectedEnergija: */ 90.0),
                Arguments.of("upotrebiMagiju_ShouldReturnAnAdequateResult_" +
                                "IfInteligencijaIsLessThanPotrebnaInteligencija_" +
                                "AndZdravljeIsGreaterThanPotrebnaEnergija"
                        , /* potrebnaEnergija: */ 10.0, /* potrebnaInteligencija: */ 10.0, /* steta: */ 10.0,
                        /* zdravlje: */ 100.0, /* energija: */ 100.0, /* inteligencija: */ 9,
                        /* expectedResult: */ 39.0, /* expectedZdravlje: */ 80.0, /* expectedEnergija: */ 0),
                Arguments.of("upotrebiMagiju_ShouldReturnAnAdequateResult_" +
                                "IfInteligencijaIsLessThanPotrebnaInteligencija_" +
                                "AndZdravljeIsLessThanPotrebnaEnergija"
                        , /* potrebnaEnergija: */ 10.0, /* potrebnaInteligencija: */ 5.0, /* steta: */ 10.0,
                        /* zdravlje: */ 9.0, /* energija: */ 100.0, /* inteligencija: */ 3,
                        /* expectedResult: */ 0.0, /* expectedZdravlje: */ 8.1, /* expectedEnergija: */ 0),
                Arguments.of("upotrebiMagiju_ShouldReturnAnAdequateResult_" +
                                "IfInteligencijaIsGreaterThanPotrebnaInteligencija_AndEnergijaIsLessThanPotrebnaEnergija" +
                                "WhenZdravljeIsGreaterThanPotrebnaEnergija"
                        , /* potrebnaEnergija: */ 10.0, /* potrebnaInteligencija: */ 10.0, /* steta: */ 10.0,
                        /* zdravlje: */ 100.0, /* energija: */ 9.0, /* inteligencija: */ 11,
                        /* expectedResult: */ 81.0, /* expectedZdravlje: */ 90.0, /* expectedEnergija: */ 0),
                Arguments.of("upotrebiMagiju_ShouldReturnAnAdequateResult_" +
                                "IfInteligencijaIsGreaterThanPotrebnaInteligencija_AndEnergijaIsGreaterThanPotrebnaEnergija" +
                                "WhenZdravljeIsLessThanPotrebnaEnergija"
                        , /* potrebnaEnergija: */ 10.0, /* potrebnaInteligencija: */ 10.0, /* steta: */ 10.0,
                        /* zdravlje: */ 9.0, /* energija: */ 100.0, /* inteligencija: */ 11,
                        /* expectedResult: */ 81.0, /* expectedZdravlje: */ 9.0, /* expectedEnergija: */ 90.0),
                Arguments.of("upotrebiMagiju_ShouldReturnAnAdequateResult_" +
                                "IfInteligencijaIsGreaterThanPotrebnaInteligencija_AndEnergijaIsLessThanPotrebnaEnergija" +
                                "WhenZdravljeIsLessThanPotrebnaEnergija"
                        , /* potrebnaEnergija: */ 5.0, /* potrebnaInteligencija: */ 10.0, /* steta: */ 10.0,
                        /* zdravlje: */ 9.0, /* energija: */ 4.0, /* inteligencija: */ 11,
                        /* expectedResult: */ 0.0, /* expectedZdravlje: */ 4.0, /* expectedEnergija: */ 0.0)

        );
    }

    @Test
    void toString1_ShouldPrintOutObjectInString() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.5, 100.8, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        String expected = "(" + "Player1" + ", " + 150.5 + "/" + 100.8 + ", " + "STR:" + 20 + ", " + "INT:" + 10 + ")";
        String actual = p1.toString();
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getParametersForNapadniIgraca")
    void napadniIgraca_ShouldReturnAnAdequateResult(String ime, double potrebnaSnaga, double getSteta, double energija, int snaga, double expected, Igrac.Stanje stanje) {
        //Korišćenjem Mockito bilblioteke, podešavaju se atribut za klase koje još nisu implementirane
        Mockito.when(oruzje.getSteta()).thenReturn(getSteta);
        Mockito.when(oruzje.getPotrebnaSnaga()).thenReturn(potrebnaSnaga);
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();
        oruzjeList.add(oruzje);

        Igrac p1 = new Igrac("Player1", 150.0, energija, snaga, 10, stanje, oruzjeList, odecaList, magijaList);
        Igrac p2 = new Igrac("Player2", 150.0, 100.0, 20, 10, Igrac.Stanje.PASIVNO, oruzjeList, odecaList, magijaList);

        Assertions.assertAll(
                () -> assertEquals(expected, p1.napadniIgraca(0, p2)),
                () -> assertEquals(energija - 21, p1.getEnergija())
        );
    }

    @Test
    void napadniIgraca_ShouldReturnZero_IfEnergijaIsLessThan21() {
        Mockito.when(oruzje.getSteta()).thenReturn(10.0);
        Mockito.when(oruzje.getPotrebnaSnaga()).thenReturn(10.0);
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();
        oruzjeList.add(oruzje);
        double energija = 20.0;

        Igrac p1 = new Igrac("Player1", 150.0, energija, 10, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        Igrac p2 = new Igrac("Player2", 150.0, 100.0, 20, 10, Igrac.Stanje.PASIVNO, oruzjeList, odecaList, magijaList);

        Assertions.assertAll(
                () -> assertEquals(0, p1.napadniIgraca(0, p2)),
                () -> assertEquals(energija, p1.getEnergija())
        );
    }

    @Test
    void odbraniSe_ShouldThrowIllegalArgumentException_IfValueOfStetaIsNegative() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.0, 100.0, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);

        Assertions.assertThrows(IllegalArgumentException.class, () -> p1.odbraniSe(-1));
    }

    @Test
    void odbraniSe_ShouldNotThrowIllegalArgumentException_IfValueOfStetaIsZero() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.0, 100.0, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);

        Assertions.assertThrows(IllegalArgumentException.class, () -> p1.odbraniSe(0));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getParametersForOdbraniSe")
    void odbraniSe_ShouldReturnAnAdequateResult(String ime, double energija, int snaga, double potrebnaTezina, double odbrambenaVrednost, Igrac.Stanje stanje, double expected) {
        Mockito.when(odeca.getOdbrambenaVrednost()).thenReturn(odbrambenaVrednost);
        Mockito.when(odeca.getTezina()).thenReturn(potrebnaTezina);

        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();
        odecaList.add(odeca);
        odecaList.add(odeca);

        Igrac p1 = new Igrac("Player1", 150.0, energija, snaga, 10, stanje, oruzjeList, odecaList, magijaList);

        Assertions.assertEquals(expected, p1.odbraniSe(10), 0.01);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getParametersForUpotrebiMagiju")
    void upotrebiMagiju_ShouldReturnAnAdequateResult(String ime, double potrebnaEnergija, double potrebnaInteligencija,
                                                     double steta, double zdravlje, double energija, int inteligencija,
                                                     double expected, double expectedZdravlje, double expectedEnergija) {
        Mockito.when(magija.getPotrebnaEnergija()).thenReturn(potrebnaEnergija);
        Mockito.when(magija.getPotrebnaInteligencija()).thenReturn(potrebnaInteligencija);
        Mockito.when(magija.getSteta()).thenReturn(steta);

        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();
        magijaList.add(magija);

        Igrac p1 = new Igrac("Player1", zdravlje, energija, 20, inteligencija, Igrac.Stanje.PASIVNO, oruzjeList, odecaList, magijaList);
        Igrac p2 = new Igrac("Player2", 150.0, 100.0, 20, 5, Igrac.Stanje.PASIVNO, oruzjeList, odecaList, magijaList);

        Assertions.assertAll(
                () -> assertEquals(expected, p1.upotrebiMagiju(0, p2), 0.01),
                () -> assertEquals(expectedZdravlje, p1.getZdravlje()),
                () -> assertEquals(expectedEnergija, p1.getEnergija())
        );
    }

    @ParameterizedTest
    @MethodSource("returnValuesOfOdmoriSe")
    void odmoriSe_ShouldReturnAdequateResult_IfAdequateValueOfEnergijaIsSent(double energija, double oporavljenEnergija) {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.5, energija, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        Assertions.assertEquals(oporavljenEnergija, p1.odmoriSe());
    }


    @Test
    void getNaziv_ShouldReturStringNaziv() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.5, 100.8, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        String expected = "Player1";
        String actual = p1.getNaziv();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setNaziv_ShouldSetStringNazivToInputValue() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.5, 100.8, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        final String setNaziv = "Player2";
        p1.setNaziv(setNaziv);
        Assertions.assertEquals(setNaziv, p1.getNaziv());
    }

    @Test
    void getZdravlje_ShouldReturnDoubleZdravlje() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.5, 100.8, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        double expected = 150.5;
        double actual = p1.getZdravlje();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setZdravlje_ShouldSetDoubleZdravljeToInputValue() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.5, 100.8, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        final double setZdravlje = 200.15;
        p1.setZdravlje(setZdravlje);
        Assertions.assertEquals(setZdravlje, p1.getZdravlje());
    }

    @Test
    void getEnergija_ShouldReturnDoubleEnergija() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.5, 100.8, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        double expected = 100.8;
        double actual = p1.getEnergija();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setEnergija_ShouldSetDoubleEnergijaToInputValue() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.5, 100.8, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        final double setEnergija = 165.6;
        p1.setEnergija(setEnergija);
        Assertions.assertEquals(setEnergija, p1.getEnergija());
    }

    @Test
    void getSnaga_ShouldReturnIntSnaga() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.5, 100.8, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        int expected = 20;
        int actual = p1.getSnaga();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setSnaga_ShouldSetIntSnagaToInputValue() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.5, 100.8, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        final int setSnaga = 30;
        p1.setSnaga(setSnaga);
        Assertions.assertEquals(setSnaga, p1.getSnaga());
    }

    @Test
    void getInteligencija_ShouldReturnIntInteligencija() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.5, 100.8, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        int expected = 10;
        int actual = p1.getInteligencija();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setInteligencija_ShouldSetIntInteligencijaToInputValue() {
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.5, 100.8, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);
        final int setInteligencija = 50;
        p1.setInteligencija(setInteligencija);
        Assertions.assertEquals(setInteligencija, p1.getInteligencija());
    }

    @Test
    void getStanje_ShouldReturnIgracStanje() {
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();
        Igrac.Stanje stanje = Igrac.Stanje.DEFANZIVNO;

        Igrac p1 = new Igrac("Player1", 150.0, 100.0, 20, 10, stanje, oruzjeList, odecaList, magijaList);

        Assertions.assertEquals(stanje, p1.getStanje());
    }

    @Test
    void setStanje_ShouldSetNewIgracStanje() {
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();
        Igrac.Stanje stanje = Igrac.Stanje.DEFANZIVNO;

        Igrac p1 = new Igrac("Player1", 150.0, 100.0, 20, 10, stanje, oruzjeList, odecaList, magijaList);

        Igrac.Stanje stanje2 = Igrac.Stanje.AGRESIVNO;
        p1.setStanje(stanje2);

        Assertions.assertEquals(stanje2, p1.getStanje());
    }

    @Test
    void getOruzja_ShouldReturnOruzjeArrayList() {
        Mockito.when(oruzje.getSteta()).thenReturn(10.0);
        Mockito.when(oruzje.getPotrebnaSnaga()).thenReturn(10.0);
        Mockito.when(oruzje.getTezina()).thenReturn(10.0);
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();
        oruzjeList.add(oruzje);

        Igrac p1 = new Igrac("Player1", 150.0, 100.0, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);

        Assertions.assertEquals(oruzjeList, p1.getOruzja());
    }

    @Test
    void setOruzja_ShouldSetNewOruzjeArrayList() {
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.0, 100.0, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);

        ArrayList<Oruzje> oruzjeList2 = new ArrayList<>();
        p1.setOruzja(oruzjeList2);

        Assertions.assertEquals(oruzjeList2, p1.getOruzja());
    }

    @Test
    void getOdeca_ShouldReturnOdecaArrayList() {
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.0, 100.0, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);

        Assertions.assertEquals(odecaList, p1.getOdeca());
    }

    @Test
    void setOdeca_ShouldSetNewOdecaArrayList() {
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.0, 100.0, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);

        ArrayList<Odeca> odecaList2 = new ArrayList<>();
        p1.setOdeca(odecaList2);

        Assertions.assertEquals(odecaList2, p1.getOdeca());
    }

    @Test
    void getMagije_ShouldReturnMagijaArrayList() {
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.0, 100.0, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);

        Assertions.assertEquals(magijaList, p1.getMagije());
    }

    @Test
    void setMagije_ShouldSetNewMagijaArrayList() {
        ArrayList<Odeca> odecaList = new ArrayList<>();
        ArrayList<Oruzje> oruzjeList = new ArrayList<>();
        ArrayList<Magija> magijaList = new ArrayList<>();

        Igrac p1 = new Igrac("Player1", 150.0, 100.0, 20, 10, Igrac.Stanje.DEFANZIVNO, oruzjeList, odecaList, magijaList);

        ArrayList<Magija> maijaList2 = new ArrayList<>();
        p1.setMagije(maijaList2);

        Assertions.assertEquals(maijaList2, p1.getMagije());
    }
}