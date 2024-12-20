public class City {
    private String name;
    private City[] connections;
    private int[] times;
    private int connectionCount;

    public City(String name) {
        this.name = name;
        this.connections = new City[10];
        this.times = new int[10];
        this.connectionCount = 0;
    }
    public int getConnectionCount() {
        return connectionCount;
    }
    public City[] getConnections() {
        return connections;
    }
    public int[] getTimes() {
        return times;
    }

    public String getName() {
        return name;
    }

    public void addConnection(City city, int time) {
        if (connectionCount == connections.length) {
            City[] newConnections = new City[connectionCount * 2];
            int[] newTimes = new int[connectionCount * 2];
            System.arraycopy(connections, 0, newConnections, 0, connectionCount);
            System.arraycopy(times, 0, newTimes, 0, connectionCount);
            connections = newConnections;
            times = newTimes;
        }
        connections[connectionCount] = city;
        times[connectionCount] = time;
        connectionCount++;
    }
}

