import java.util.*;
import java.time.LocalDate;

// Kruskal's Minimum Spanning Tree Implementation
class Edge {
    int source;
    int destination;
    int weight;

    Edge(int source, int destination, int weight) {
        this.source = source; 
        this.destination = destination; 
        this.weight = weight; 
    }
}

class NetworkConnectionOptimization {
    private List<Edge> edges;
    private int numberOfCities;

    public NetworkConnectionOptimization(int numberOfCities) {
        this.numberOfCities = numberOfCities;
        edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    private boolean formsCycle(int[][] parent, int source, int destination) {
        while (parent[source][0] != -1) {
            source = parent[source][0];
        }
        while (parent[destination][0] != -1) {
            destination = parent[destination][0];
        }
        return source == destination; 
    }

    public void findMinimumSpanningTree() {
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));
        int[][] parent = new int[numberOfCities][1];
        for (int i = 0; i < numberOfCities; i++) {
            parent[i][0] = -1;
        }
        List<Edge> result = new ArrayList<>();
        int totalCost = 0;
        for (Edge edge : edges) {
            if (!formsCycle(parent, edge.source, edge.destination)) {
                result.add(edge);
                totalCost += edge.weight;
                parent[edge.destination][0] = edge.source;
            }
        }
        System.out.println("Minimum Spanning Tree Connections:");
        for (Edge edge : result) {
            System.out.printf("City %d -- City %d (Cost: %d)\n", edge.source, edge.destination, edge.weight);
        }
        System.out.println("Total Cost to connect all cities: " + totalCost);
    }
}

// Priority Task Scheduler
class Task {
    String name;
    LocalDate dueDate;
    int urgency;
    int weightage;

    Task(String name, LocalDate dueDate, int urgency, int weightage) {
        this.name = name;
        this.dueDate = dueDate;
        this.urgency = urgency;
        this.weightage = weightage;
    }
}

class TaskScheduler {
    PriorityQueue<Task> taskQueue;

    public TaskScheduler() {
        taskQueue = new PriorityQueue<>(Comparator.comparingInt(task -> -task.urgency));
    }

    public void addTask(Task task) {
        taskQueue.offer(task);
    }

    public List<Task> getOrderedTasks() {
        List<Task> orderedTasks = new ArrayList<>();
        while (!taskQueue.isEmpty()) {
            orderedTasks.add(taskQueue.poll());
        }
        return orderedTasks;
    }
}

public class CombinedProgram {
    public static void main(String[] args) {
        NetworkConnectionOptimization graph = new NetworkConnectionOptimization(4);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 7);
        graph.addEdge(2, 3, 8);
        graph.addEdge(3, 0, 6);
        graph.findMinimumSpanningTree();

        TaskScheduler scheduler = new TaskScheduler();
        scheduler.addTask(new Task("ADS Assignment", LocalDate.of(2024, 11, 5), 4, 30));
        scheduler.addTask(new Task("Cloud Computing ppt", LocalDate.of(2024, 11, 1), 5, 20));
        scheduler.addTask(new Task("Capstone project", LocalDate.of(2024, 10, 28), 3, 50));
        scheduler.addTask(new Task("Smart Cities assignment", LocalDate.of(2024, 11, 3), 2, 10));

        List<Task> orderedTasks = scheduler.getOrderedTasks();
        System.out.println("Ordered tasks for completion:");
        for (Task task : orderedTasks) {
            System.out.printf("Task: %s, Due Date: %s, Urgency: %d, Weightage: %d%%\n",
                    task.name, task.dueDate, task.urgency, task.weightage);
        }
    }
}
