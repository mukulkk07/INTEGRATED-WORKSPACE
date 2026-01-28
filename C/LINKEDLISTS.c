#include <stdio.h>
#include <stdlib.h>

// ==========================================
// 1. NODE DEFINITION
// ==========================================
// In C, we define the structure first.
typedef struct Node {
    int data;
    struct Node* next;
} Node;

// Helper function to create a new node
// (Equivalent to the C++ Constructor)
Node* createNode(int val) {
    // Step 1: Request memory block of size 'Node' from the heap
    Node* newNode = (Node*)malloc(sizeof(Node));
    
    // Step 2: Set values
    newNode->data = val;
    newNode->next = NULL;
    
    return newNode;
}

// ==========================================
// 2. LINKED LIST OPERATIONS
// ==========================================

// --- INSERTION OPERATIONS ---

// We pass 'Node** head_ref' (Pointer to Pointer).
// Why? Because we might need to change the ACTUAL head variable in main().
void insertAtHead(Node** head_ref, int val) {
    // Step 1: Create the new node
    Node* newNode = createNode(val);

    // Step 2: Point new node's next to the CURRENT head
    // (*head_ref) dereferences the double pointer to get the actual head node
    newNode->next = *head_ref;

    // Step 3: Update the real head pointer to point to this new node
    *head_ref = newNode;

    printf("Inserted %d at head.\n", val);
}

void insertAtTail(Node** head_ref, int val) {
    Node* newNode = createNode(val);

    // Edge Case: If list is empty, make new node the head
    if (*head_ref == NULL) {
        *head_ref = newNode;
        printf("Inserted %d at tail.\n", val);
        return;
    }

    // Step 1: Use a temporary pointer to traverse.
    // We start at the current head.
    Node* temp = *head_ref;

    // Step 2: Move to the last node (where next is NULL)
    while (temp->next != NULL) {
        temp = temp->next;
    }

    // Step 3: Link the last node to the new node
    temp->next = newNode;
    printf("Inserted %d at tail.\n", val);
}

// --- DELETION OPERATIONS ---

void deleteHead(Node** head_ref) {
    // Edge Case: List is empty
    if (*head_ref == NULL) {
        printf("List is empty.\n");
        return;
    }

    // Step 1: Hold the current head in a temp variable
    Node* temp = *head_ref;

    // Step 2: Move the head pointer to the next node
    *head_ref = (*head_ref)->next;

    // Step 3: Free the memory of the old head
    free(temp);

    printf("Deleted head node.\n");
}

void deleteValue(Node** head_ref, int val) {
    // Edge Case: List is empty
    if (*head_ref == NULL) return;

    // Special Case: If the head itself holds the value
    if ((*head_ref)->data == val) {
        deleteHead(head_ref);
        return;
    }

    // Step 1: Traverse to find the node BEFORE the target
    Node* temp = *head_ref;

    while (temp->next != NULL && temp->next->data != val) {
        temp = temp->next;
    }

    // If we reached the end and didn't find it
    if (temp->next == NULL) {
        printf("Value %d not found.\n", val);
        return;
    }

    // Step 2: 'toDelete' is the node we want to remove
    Node* toDelete = temp->next;

    // Step 3: Unlink the node (Skip over it)
    temp->next = temp->next->next;

    // Step 4: Free memory
    free(toDelete);

    printf("Deleted value %d.\n", val);
}

// --- REVERSE OPERATION ---

void reverse(Node** head_ref) {
    Node* prev = NULL;
    Node* current = *head_ref;
    Node* next = NULL;

    while (current != NULL) {
        // 1. Store next node
        next = current->next;

        // 2. Reverse current node's pointer
        current->next = prev;

        // 3. Move pointers one step forward
        prev = current;
        current = next;
    }

    // 4. Update the actual head to point to the new front (prev)
    *head_ref = prev;
    printf("List reversed.\n");
}

// --- DISPLAY ---
// (We only need 'Node* head' here, not 'Node**', because we don't modify the head)
void display(Node* head) {
    printf("List: ");
    while (head != NULL) {
        printf("%d -> ", head->data);
        head = head->next;
    }
    printf("NULL\n");
}

// ==========================================
// 3. MAIN EXECUTION
// ==========================================
int main() {
    // Initialize Head to NULL (Empty list)
    Node* head = NULL;

    // Note: We pass &head (address of head) to insertion functions
    // so they can modify the pointer.

    // 1. Add 10. List: 10 -> NULL
    insertAtHead(&head, 10);

    // 2. Add 5 at head. List: 5 -> 10 -> NULL
    insertAtHead(&head, 5);

    // 3. Add 20 at tail. List: 5 -> 10 -> 20 -> NULL
    insertAtTail(&head, 20);

    // Show current state
    display(head);

    // 4. Delete the 5 (Head). List: 10 -> 20 -> NULL
    deleteHead(&head);
    display(head);

    // 5. Reverse list. List: 20 -> 10 -> NULL
    reverse(&head);
    display(head);

    return 0;
}