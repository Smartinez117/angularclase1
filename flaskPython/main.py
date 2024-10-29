from flask import Flask, jsonify, request
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

# Configuración de la conexión a la base de datos
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://username:basededatosdesarrollo@localhost:3306/tablaprueba'  
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)

# Definición del modelo
class Persona(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(50), nullable=False)
    apellido = db.Column(db.String(50), nullable=False)
    edad = db.Column(db.Integer, nullable=False)
    ocupacion = db.Column(db.String(50), nullable=False)

    def to_dict(self):
        return {
            "id": self.id,
            "nombre": self.nombre,
            "apellido": self.apellido,
            "edad": self.edad,
            "ocupacion": self.ocupacion
        }

# Ruta de inicio
@app.route('/')
def home():
    return "<h1>Bienvenido a la API de Personas</h1><p>Utiliza las rutas /personas para obtener datos.</p>"

# Ruta para obtener todas las personas
@app.route('/personasGet', methods=['GET'])
def get_personas():
    personas = Persona.query.all()
    return jsonify([persona.to_dict() for persona in personas])

# Ruta para agregar una nueva persona
@app.route('/personasPost', methods=['POST'])
def add_persona():
    data = request.get_json()
    nueva_persona = Persona(
        nombre=data['nombre'],
        apellido=data['apellido'],
        edad=data['edad'],
        ocupacion=data['ocupacion']
    )
    db.session.add(nueva_persona)
    db.session.commit()
    return jsonify(nueva_persona.to_dict()), 201

# Ruta para obtener una persona por ID
@app.route('/personas/<int:id>', methods=['GET'])
def get_persona(id):
    persona = Persona.query.get_or_404(id)
    return jsonify(persona.to_dict())

# Ruta para actualizar una persona por ID
@app.route('/personas/<int:id>', methods=['PUT'])
def update_persona(id):
    persona = Persona.query.get_or_404(id)
    data = request.get_json()
    
    persona.nombre = data.get('nombre', persona.nombre)
    persona.apellido = data.get('apellido', persona.apellido)
    persona.edad = data.get('edad', persona.edad)
    persona.ocupacion = data.get('ocupacion', persona.ocupacion)

    db.session.commit()
    
    return jsonify(persona.to_dict())

# Ruta para eliminar una persona por ID
@app.route('/personas/<int:id>', methods=['DELETE'])
def delete_persona(id):
    persona = Persona.query.get_or_404(id)
    db.session.delete(persona)
    db.session.commit()
    
    return '', 204

if __name__ == '__main__':
    app.run(debug=True)