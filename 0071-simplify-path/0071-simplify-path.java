import java.util.Stack;

class Solution {
    public String simplifyPath(String path) {
        // Split the path by one or more slashes
        String[] components = path.split("/");
        Stack<String> stack = new Stack<>();
        
        for (String component : components) {
            // If the component is empty or a single dot, ignore it
            if (component.isEmpty() || component.equals(".")) {
                continue;
            }
            // If it's a double dot, go up one directory level if possible
            if (component.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // It's a valid directory or file name (e.g., "home" or "...")
                stack.push(component);
            }
        }
        
        // Reconstruct the canonical path from the stack
        StringBuilder canonicalPath = new StringBuilder();
        for (String dir : stack) {
            canonicalPath.append("/").append(dir);
        }
        
        // If the stack was empty, return "/" (root)
        return canonicalPath.length() == 0 ? "/" : canonicalPath.toString();
    }
}