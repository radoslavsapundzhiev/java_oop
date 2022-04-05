package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class HeroRepositoryTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS HeroRepository
    private HeroRepository heroRepository;
    private Hero pesho;
    private Hero gosho;
    private Hero kiro;

    @Before
    public void setUp() {
        heroRepository = new HeroRepository();
        pesho = new Hero("Pesho", 100);
        gosho = new Hero("Gosho", 80);
        kiro = new Hero("Kiro", 110);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateWithNullHeroShouldThrow() {
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithExistingHeroNameShouldThrow() {
        heroRepository.create(pesho);
        Hero hero = new Hero("Pesho", 90);
        heroRepository.create(hero);
    }

    @Test
    public void testCreateHeroWithCorrectName() {
        String actualResult =  heroRepository.create(pesho);
        Assert.assertEquals(1, heroRepository.getCount());
        String expectedResult = String.format("Successfully added hero %s with level %d", pesho.getName(), pesho.getLevel());
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveHeroWithNullHeroNameShouldThrow() {
        heroRepository.create(pesho);
        heroRepository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveHeroWithEmptyHeroNameShouldThrow() {
        heroRepository.create(pesho);
        heroRepository.remove("     ");
    }

    @Test
    public void testRemoveWithCorrectName() {
        heroRepository.create(pesho);
        heroRepository.create(gosho);
        boolean actualResult = heroRepository.remove("Pesho");
        Assert.assertTrue(actualResult);
    }

    @Test
    public void testGetHeroWithHighestLevel() {
        heroRepository.create(pesho);
        heroRepository.create(gosho);
        heroRepository.create(kiro);
        Hero hero = heroRepository.getHeroWithHighestLevel();
        Assert.assertEquals(kiro.getName(), hero.getName());
        Assert.assertEquals(kiro.getLevel(), kiro.getLevel());
    }

    @Test
    public void testGetHeroIfExists() {
        heroRepository.create(pesho);
        heroRepository.create(gosho);
        heroRepository.create(kiro);
        Hero hero = heroRepository.getHero("Pesho");
        Assert.assertEquals(hero.getName(), pesho.getName());
        Assert.assertEquals(hero.getLevel(), pesho.getLevel());
    }

    @Test
    public void testGetHeroIfNotExists() {
        heroRepository.create(pesho);
        heroRepository.create(gosho);
        heroRepository.create(kiro);
        Hero hero = heroRepository.getHero("Pesho1");
        Assert.assertNull(hero);
    }

    @Test
    public void testGetHeroes() {
        heroRepository.create(pesho);
        heroRepository.create(gosho);
        heroRepository.create(kiro);
        Collection<Hero> heroes = heroRepository.getHeroes();
        Assert.assertEquals(3, heroes.size());
    }
}
