from py.thrift import ttypes


class PersonServiceImpl:
    def getPersonByUsername(self, username):
        print("getPersonByUsername called")
        print(username)
        newPerson = ttypes.Person()
        newPerson.name = username
        newPerson.age = 24
        newPerson.married = True
        return newPerson

    def savePerson(self, person):
        print("savePerson called")
        print(person.name)
        print(person.age)
        print(person.married)
