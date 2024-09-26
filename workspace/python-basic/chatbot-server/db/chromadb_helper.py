import openai
import chromadb
from chromadb.config import Settings, DEFAULT_DATABASE, DEFAULT_TENANT

# chromadb_client = chromadb.Client()
from chromadb.config import DEFAULT_TENANT, DEFAULT_DATABASE, Settings

chromadb_client = chromadb.PersistentClient(
    path="vectordb",
    settings=Settings(),
    tenant=DEFAULT_TENANT,
    database=DEFAULT_DATABASE,
)

collection_name = "recruit_documents"

# recruit_collection = chromadb_client.get_or_create_collection(name=collection_name)
# recruit_collection = chromadb_client.create_collection(name="recruit_documents")
recruit_collection = chromadb_client.get_collection(name="recruit_documents")
# recruit_collection = None

def generate_embedding(text):
    response = openai.embeddings.create(
        input=text,
        model="text-embedding-ada-002"
    )

    # return response['data'][0]['embedding']
    return response.data[0].embedding

def store_document(text):
    embedding = generate_embedding(text)

    recruit_collection.add(
        documents=[text],
        embeddings=[embedding],
        ids=[text[:50]]
    )

def query_similar_documents(query, top_k=5):
    query_embedding = generate_embedding(query)

    results = recruit_collection.query( # 유사도를 기반으로 관련성이 높은 데이터 조회 (consine similarity 기본)
        query_embeddings=[query_embedding],
        n_results=top_k
    )

    return results

def delete_collection():
    chromadb_client.delete_collection(collection_name)    

if __name__ == "__main__":

    documents = [
        "The quick brown fox jumps over the lazy dog.",
        "Artificial intelligence is transforming many industries by enabling automation.",
        "Python is a versatile programming language used widely in data science.",
        "Cats are known for their agility and independence.",
        "OpenAI is advancing the field of AI with innovations like GPT-4."
    ]

    # Store documents and embeddings
    for doc in documents:
        store_document(doc)

    # Example query to find similar documents
    query = "Tell me about artificial intelligence and its impact."
    print("\nQuerying similar documents...\n")
    selected_documents = query_similar_documents(query, top_k=3)
    print(len(selected_documents['documents'][0]), len(selected_documents['distances']))
    for doc, score in zip(selected_documents['documents'][0], selected_documents['distances'][0]):
        print(doc[:30], score)

    delete_collection()