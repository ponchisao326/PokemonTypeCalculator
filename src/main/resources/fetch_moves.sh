#!/bin/bash

CSV_FILE="pokemon_moves.csv"
API_BASE="https://pokeapi.co/api/v2/move"

echo "id,name,type,power,accuracy,pp,damage_class,effect" > $CSV_FILE

for ((id=1; id<=1000; id++)); do
    echo "Fetching move $id..."

    data=$(curl -s "$API_BASE/$id")

    # Verificar si el movimiento existe
    name=$(echo $data | jq -r '.name')
    if [[ "$name" == "null" || -z "$name" ]]; then
        echo "Move ID $id not found, skipping..."
        continue
    fi

    # Extraer datos
    type=$(echo $data | jq -r '.type.name // ""')
    power=$(echo $data | jq -r '.power // ""')
    accuracy=$(echo $data | jq -r '.accuracy // ""')
    pp=$(echo $data | jq -r '.pp // ""')
    damage_class=$(echo $data | jq -r '.damage_class.name // ""')

    # Procesar efecto y limpiar formato
    effect=$(echo $data | jq -r '.effect_entries[] | select(.language.name == "en").effect | gsub("[\\n\\t]"; " ") // ""' | tr -d '\n' | sed 's/  / /g')

    # Escribir en CSV
    echo "$id,$name,$type,$power,$accuracy,$pp,$damage_class,\"$effect\"" >> $CSV_FILE
done

echo "Base de datos de movimientos generada en: $CSV_FILE"