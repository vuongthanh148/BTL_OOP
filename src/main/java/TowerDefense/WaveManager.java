package TowerDefense;

public class WaveManager {
    private float timeSinceLastWave, timeBetweenEnemies;
    private int waveNumber, enemiesPerWave;
    private Enemy enemyType;
    private Wave currentWave;

    public WaveManager(Enemy enemyType, float timeBetweenEnemies, int enemiesPerWave ) {
        this.enemiesPerWave = enemiesPerWave;
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.enemyType = enemyType;
        this.timeSinceLastWave = 0;
        this.waveNumber = 0;

        this.currentWave = null;

        newWave();
    }

    public void Update(){
        if(currentWave!=null) currentWave.Update();
    }

    private void newWave(){
        currentWave = new Wave(enemyType, timeBetweenEnemies, enemiesPerWave);
        waveNumber++;
        System.out.println("Beginning Wave: " + waveNumber);
    }

}
