package com.fantasystocks.integration;

import com.fantasystocks.config.TestConfig;
import com.fantasystocks.entity.Portfolio;
import com.fantasystocks.entity.Stock;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Slf4j
public class PortfoliosAndGameIntegrationTest extends IntegrationTestScaffold {
    private static ImmutableList<String> offenseTest = ImmutableList.of("GOOGL", "AMZN", "MSFT", "FB", "NVDA");
    private static List<Stock> offense = generateStocksFromSymbols(offenseTest);
    private static ImmutableList<String> defenseTest = ImmutableList.of("TSLA", "NKE", "AAPL", "CAT");
    private static List<Stock> defense = generateStocksFromSymbols(defenseTest);
    private static ImmutableList<String> benchTest = ImmutableList.of("SPY", "SPLG", "VTHR", "IBB", "IGM");
    private static List<Stock> bench = generateStocksFromSymbols(benchTest);

    public static List<Stock> generateStocksFromSymbols(List<String> symbols) {
        return symbols.stream()
                .map(s -> Stock.builder().symbol(s).build())
                .collect(Collectors.toList());
    }

    @Test
    public void test_addPortfolio() {
        Portfolio expected = buildPortfolio(offense, defense, bench);
        Long portfolioId = (Long) session.save(expected);

        Portfolio actual = session.get(Portfolio.class, portfolioId);
        assertEquals("The portfolios are not the same.", expected, actual);
    }

}
